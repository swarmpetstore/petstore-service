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

    private String serviceHost;

    @PostConstruct
    public void getHost() {
        serviceHost = System.getenv("CATALOG_SERVICE_SERVICE_HOST");
    }

    public List<Item> getAllItems(){
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://" + serviceHost +":8080/item/");
        return Arrays.asList(target.request(MediaType.APPLICATION_JSON).get(Item[].class));
    }

    public List<Item> getItem(String name){
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://" + serviceHost +":8080/item/"+name);
        return Arrays.asList(target.request(MediaType.APPLICATION_JSON).get(Item.class));
    }

}