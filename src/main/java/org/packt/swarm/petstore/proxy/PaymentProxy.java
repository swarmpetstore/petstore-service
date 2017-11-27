package org.packt.swarm.petstore.proxy;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
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
        targetPath = "http://" + hostname + ":" + SWARM_PORT;
    }

    public String makePayment(int orderId){
        return new CreatePaymentCommand(orderId).execute();

    }

    private class CreatePaymentCommand extends HystrixCommand<String> {

        private final int orderId;

        public CreatePaymentCommand(int orderId) {
            super(HystrixCommandGroupKey.Factory.asKey(SERVICE_NAME));
            this.orderId = orderId;
        }

        @Override
        protected String run() {
            Client client = ClientBuilder.newClient();
            WebTarget target = client.target(targetPath + "/payment");
            target.queryParam("merchantId","1");
            target.queryParam("description", "ORDER ID: "+orderId);
            target.queryParam("amount", 10);
            String paymentUID = target.request(MediaType.APPLICATION_JSON).post(Entity.entity("pies", MediaType.APPLICATION_JSON_TYPE), String.class);
            return null;
        }
    }

}
