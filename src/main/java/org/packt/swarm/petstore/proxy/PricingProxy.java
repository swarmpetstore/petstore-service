package org.packt.swarm.petstore.proxy;

import org.packt.swarm.petstore.model.Price;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

@ApplicationScoped
public class PricingProxy {

    private String pricingServiceHost;
    private String pricingServicePort;

    public PricingProxy() {
        pricingServiceHost = System.getenv("PRICING_SERVICE_SERVICE_HOST");
        pricingServicePort = System.getenv("PRICING_SERVICE_SERVICE_PORT");
    }

    public Price getPrice(String name){
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://" + pricingServiceHost +":" + pricingServicePort + "/price/" + name);
        return target.request(MediaType.APPLICATION_JSON).get(Price.class);
    }
}
