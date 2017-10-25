package org.packt.swarm.petstore.proxy;

import org.packt.swarm.petstore.model.Item;

import javax.annotation.PostConstruct;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.List;

/**
 * Created by tomek on 25.10.17.
 */
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
