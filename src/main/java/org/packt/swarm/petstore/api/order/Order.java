package org.packt.swarm.petstore.api.order;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private Long id;

    private int customerId;
    @JsonManagedReference
    private final List<Item> items = new ArrayList<>();
    @JsonIgnore
    private double price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

        @JsonBackReference
        private Order order;

        private int itemId;
        private int quantity;


        public Item(Order order, int itemId, int quantity){
            this.order = order;
            this.itemId = itemId;
            this.quantity = quantity;
        }


        public Order getOrder() {
            return order;
        }

        public void setOrder(Order order) {
            this.order = order;
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