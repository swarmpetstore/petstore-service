package org.packt.swarm.petstore.proxy;

import org.wildfly.swarm.topology.Topology;

import javax.naming.NamingException;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.net.InetSocketAddress;
import java.util.Optional;

public class ServiceProxy {

    private final String serviceName;

    private InetSocketAddress serviceAddress = null;


    public ServiceProxy(String serviceName) throws NamingException {

        this.serviceName = serviceName;

        Topology topology = Topology.lookup();
        topology.addListener((t) -> obtainServiceAddress(t));

        obtainServiceAddress(topology);

    }

    private synchronized void obtainServiceAddress(Topology topology){
        Optional<Topology.Entry> serviceEntry = topology.asMap().get(serviceName).stream().filter(e -> e.getTags().contains("http")).findAny();
        if(serviceEntry.isPresent()){
            serviceAddress = new InetSocketAddress(serviceEntry.get().getAddress(),serviceEntry.get().getPort());
        } else {
            serviceAddress = null;
        }
    }

    public boolean  isAvailable(){
        return serviceAddress != null;
    }

    public synchronized InetSocketAddress getServiceAddress(){
        return serviceAddress;
    }

    public synchronized Invocation.Builder invoke(String restPath){
        if(serviceAddress == null){
            throw new ServiceUnavailableException();
        }
        return ClientBuilder.newClient().target("http://" + getServiceAddress().getHostString() +":" + getServiceAddress().getPort() + "/" + restPath).request(MediaType.APPLICATION_JSON);
    }

    public static class ServiceUnavailableException extends RuntimeException {}
}
