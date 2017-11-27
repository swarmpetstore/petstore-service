package org.packt.swarm.petstore.proxy;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.util.List;

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

    public void createOrder(int customerId, List<Integer> itemIds, List<Integer> quantities){
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(targetPath+"/order");

        target.queryParam("customerId", customerId);
        target.queryParam("itemIds", itemIds);
        target.queryParam("quantities", quantities);

        target.request(MediaType.APPLICATION_JSON).post(Entity.entity("pies", MediaType.APPLICATION_JSON));
    }


}