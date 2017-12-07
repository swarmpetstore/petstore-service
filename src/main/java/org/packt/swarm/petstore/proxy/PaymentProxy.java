package org.packt.swarm.petstore.proxy;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolProperties;
import org.packt.swarm.petstore.api.payment.Payment;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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

    public Response createPayment(Payment payment){
        return new CreatePaymentCommand(payment).execute();
    }


    private class CreatePaymentCommand extends HystrixCommand<Response> {

        private final Payment payment;

        public CreatePaymentCommand(Payment payment) {
            super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(SERVICE_NAME))
                                  .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                                          .withExecutionTimeoutInMilliseconds(10000))
                    .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter().withCoreSize(3)));
            this.payment = payment;
        }

        @Override
        protected Response run() {
            Client client = ClientBuilder.newClient();
            WebTarget target = client.target(targetPath + "/payment");
            return target.request(MediaType.APPLICATION_JSON).post(Entity.json(payment));
        }
    }

}
