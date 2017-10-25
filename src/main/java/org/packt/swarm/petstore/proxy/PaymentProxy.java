package org.packt.swarm.petstore.proxy;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

@ApplicationScoped
public class PaymentProxy {

    private static final String SERVICE_NAME = "payment-service";
    private static final String NAMESPACE = "petstore";
    private static final String SWARM_PORT = "8080";

    private String targetPath;

    @PostConstruct
    public void init() {
        String hostname = SERVICE_NAME + "." + NAMESPACE + ".svc";
        targetPath = hostname + ":" + SWARM_PORT;
    }

    public String makePayment(){
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(targetPath+"/payment");
        return target.request(MediaType.APPLICATION_JSON).get(String.class);
    }

}
