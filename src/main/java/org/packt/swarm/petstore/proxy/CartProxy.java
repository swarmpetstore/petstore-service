package org.packt.swarm.petstore.proxy;

import org.packt.swarm.petstore.api.cart.Cart;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CartProxy {

    public Cart getCart(int customerId){
        Cart cart = new Cart(5);
        cart.getItems().put(1,2);
        cart.getItems().put(4,1);
        return cart;
    }

}
