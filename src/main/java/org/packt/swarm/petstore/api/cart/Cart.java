package org.packt.swarm.petstore.api.cart;

import java.util.List;

public class Cart {

    private Integer customerId;
    private List<CartItem> items;

    public Cart(int customerId) {
        this.customerId = customerId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }
}
