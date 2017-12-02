package org.packt.swarm.petstore.api.cart;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private final Integer customerId;
    private List<Item> items;

    public Cart(int customerId) {
        this.customerId = customerId;
        this.items = new ArrayList<>();
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public double calculatePrice(){
        double price = 0;
        for(Item item: items){
            price += (item.getUnitPrice() * item.getQuantity());
        }
        return price;
    }

    public static class Item {
        private int itemId;
        private int quantity;
        private double unitPrice;

        public Item(){}

        public Item(int itemId, int quantity, double unitPrice){
            this.itemId = itemId;
            this.quantity = quantity;
            this.unitPrice = unitPrice;

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

        public double getUnitPrice() {
            return unitPrice;
        }

        public void setUnitPrice(int unitPrice) {
            this.unitPrice = unitPrice;
        }
    }
}
