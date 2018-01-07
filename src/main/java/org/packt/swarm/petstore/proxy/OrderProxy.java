package org.packt.swarm.petstore.proxy;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OrderProxy {

    private static final String SERVICE_NAME = "order-service";
    private static final String NAMESPACE = "petstore";
    private static final String SWARM_PORT = "8080";

    private String targetPath;

    @PostConstruct
    public void init() {
        String hostname = SERVICE_NAME + "." + NAMESPACE + ".svc";
        targetPath = "http://" + hostname + ":" + SWARM_PORT;
    }

//    public int createOrder(Order order){
//        Client client = ClientBuilder.newClient();
//        WebTarget target = client.target(targetPath+"/order");
//
//        return target.request(MediaType.APPLICATION_JSON).post(Entity.json(order), Integer.class);
//    }


}