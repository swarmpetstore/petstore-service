package org.packt.swarm.petstore.proxy;

import org.packt.swarm.petstore.model.Item;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.List;

@ApplicationScoped
public class CatalogProxy {

    private String catalogServiceHost;
    private String catalogServicePort;

    public CatalogProxy() {
        catalogServiceHost = System.getenv("CATALOG_SERVICE_SERVICE_HOST");
        catalogServicePort = System.getenv("CATALOG_SERVICE_SERVICE_PORT");
    }

    public List<Item> getAllItems(){
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://" + catalogServiceHost +":" + catalogServicePort + "/item/");
        return Arrays.asList(target.request(MediaType.APPLICATION_JSON).get(Item[].class));
    }

}