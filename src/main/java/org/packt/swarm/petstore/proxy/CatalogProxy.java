package org.packt.swarm.petstore.proxy;

import org.packt.swarm.petstore.model.Item;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.List;

@ApplicationScoped
public class CatalogProxy {

    private static final String SERVICE_NAME = "catalog-service";
    private static final String NAMESPACE = "petstore";
    private static final String SWARM_PORT = "8080";

    private String targetPath;

    @PostConstruct
    public void init() {
        String hostname = SERVICE_NAME + "." + NAMESPACE + ".svc";
        targetPath = "http://" + hostname + ":" + SWARM_PORT;
    }

    public List<Item> getAllItems(){
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(targetPath+"/item");
        return Arrays.asList(target.request(MediaType.APPLICATION_JSON).get(Item[].class));
    }

    public List<Item> getItem(String name){
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(targetPath+"/item/"+name);
        return Arrays.asList(target.request(MediaType.APPLICATION_JSON).get(Item.class));
    }

}