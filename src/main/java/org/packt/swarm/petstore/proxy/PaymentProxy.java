package org.packt.swarm.petstore.proxy;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.Future;

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

    public String makePayment(){
        return new InvokeCommand("payment").execute();

    }

    private class InvokeCommand extends HystrixCommand<String> {

        private final String name;

        public InvokeCommand(String name) {
            super(HystrixCommandGroupKey.Factory.asKey("Invocation"));
            this.name = name;
        }

        @Override
        protected String run() {
            Client client = ClientBuilder.newClient();
            WebTarget target = client.target(targetPath + "/payment");
            return target.request(MediaType.APPLICATION_JSON).get(String.class);
        }
    }

}
