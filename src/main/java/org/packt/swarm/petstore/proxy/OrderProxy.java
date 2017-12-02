package org.packt.swarm.petstore.proxy;

import org.packt.swarm.petstore.api.order.Order;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
public class OrderProxy {

    private static final String SERVICE_NAME = "order-service";
    private static final String NAMESPACE = "petstore";
    private static final String SWARM_PORT = "8080";

    private String targetPath;

    @PostConstruct
    public void init() {
        String hostname = SERVICE_NAME + "." + NAMESPACE + ".svc";
        targetPath = "http://" + hostname + ":" + SWARM_PORT;
    }

    public int createOrder(Order order){
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(targetPath+"/order");

        return target.request(MediaType.APPLICATION_JSON).post(Entity.json(order), Integer.class);
    }


}