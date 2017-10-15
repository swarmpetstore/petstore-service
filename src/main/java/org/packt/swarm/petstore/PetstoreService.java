package org.packt.swarm.petstore;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class PetstoreService {

    @Inject
    private CatalogProxy catalogProxy;

    @Inject
    private PricingProxy pricingProxy;



    public Pet getAvailablePets() {
        String name = "hamster";
        Price price = pricingProxy.getPrice(name);
        Pet pet = new Pet();
        pet.setName(name);
        pet.setPrice(price.getPrice());
        return pet;
    }
}
