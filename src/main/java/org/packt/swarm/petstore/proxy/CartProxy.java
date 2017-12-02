package org.packt.swarm.petstore.proxy;

import org.packt.swarm.petstore.api.cart.Cart;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CartProxy {

    public Cart getCart(int customerId){
        Cart cart = new Cart(5);
        cart.getItems().add(new Cart.Item(3,5,5));
        cart.getItems().add(new Cart.Item(4,1,10));
        return cart;
    }

}
