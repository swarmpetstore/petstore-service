package org.packt.swarm.petstore.proxy;

import org.packt.swarm.petstore.model.CartItem;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;

@ApplicationScoped
public class CartProxy {

    private static final String SERVICE_NAME = "cart-service";
    private static final String NAMESPACE = "petstore";
    private static final String SWARM_PORT = "8080";

    private String targetPath;

    @PostConstruct
    public void init() {
        String hostname = SERVICE_NAME + "." + NAMESPACE + ".svc";
        targetPath = "http://" + hostname + ":" + SWARM_PORT;
    }

    public void addToCart(String customerId, CartItem item){
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(targetPath+"/cart/"+customerId);
        Arrays.asList(target.request(MediaType.APPLICATION_JSON).post(Entity.json(item), Void.class));
    }


}
