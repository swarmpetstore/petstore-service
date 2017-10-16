package org.packt.swarm.petstore.proxy;

import org.packt.swarm.petstore.model.Price;

import javax.enterprise.context.ApplicationScoped;
import javax.naming.NamingException;

@ApplicationScoped
public class PricingProxy extends ServiceProxy {


    public PricingProxy() throws NamingException {
        super("pricing-service");
    }

    public Price getPrice(String name){
        return invoke("price/"+name).get(Price.class);
    }
}
