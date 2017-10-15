package org.packt.swarm.petstore;

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
        System.out.println("HOST TO "+pricingServiceHost);
    }

    public Price getPrice(String name){
        Client client = ClientBuilder.newClient();
        String address = "http://" + pricingServiceHost +":" + pricingServicePort + "/price/" + name;
        System.out.println("ADDRESS TO "+address);
        WebTarget target = client.target(address);
        return target.request(MediaType.APPLICATION_JSON).get(Price.class);
    }
}
