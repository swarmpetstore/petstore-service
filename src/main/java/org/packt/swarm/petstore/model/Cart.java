package org.packt.swarm.petstore.model;

import java.util.HashMap;
import java.util.Map;

public class Cart {

    private Integer customerId;
    private Map<Integer, Integer> items;

    public Cart(int customerId) {
        this.customerId = customerId;
        items = new HashMap<>();
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public Map<Integer, Integer> getItems() {
        return items;
    }
}
