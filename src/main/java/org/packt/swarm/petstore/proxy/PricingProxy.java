package org.packt.swarm.petstore.proxy;

import org.packt.swarm.petstore.model.Price;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

@ApplicationScoped
public class PricingProxy {

    private String serviceHost;

    @PostConstruct
    public void getHost() {
        serviceHost = System.getenv("PRICING_SERVICE_SERVICE_HOST");
    }

    public Price getPrice(String name){
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://" + serviceHost +":8080/price/" + name);
        return target.request(MediaType.APPLICATION_JSON).get(Price.class);
    }
}
