package org.packt.swarm.petstore.api.order;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private Integer id;

    private int customerId;
    private final List<Item> items = new ArrayList<>();
    @JsonIgnore
    private double price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public static class Item {
        private int itemId;
        private int quantity;

        public Item(int itemId, int quantity){
            this.itemId = itemId;
            this.quantity = quantity;
        }

        public int getItemId() {
            return itemId;
        }

        public void setItemId(int itemId) {
            this.itemId = itemId;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }
}