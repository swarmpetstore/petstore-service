package org.packt.swarm.petstore;

import org.packt.swarm.petstore.api.cart.Cart;
import org.packt.swarm.petstore.api.order.Order;
import org.packt.swarm.petstore.api.payment.Payment;
import org.packt.swarm.petstore.model.CartItem;
import org.packt.swarm.petstore.model.Item;
import org.packt.swarm.petstore.model.Pet;
import org.packt.swarm.petstore.model.Price;
import org.packt.swarm.petstore.proxy.CartProxy;
import org.packt.swarm.petstore.proxy.CatalogProxy;
import org.packt.swarm.petstore.proxy.OrderProxy;
import org.packt.swarm.petstore.proxy.PaymentProxy;
import org.packt.swarm.petstore.proxy.PricingProxy;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class PetstoreService {

    @Inject
    private CatalogProxy catalogProxy;

    @Inject
    private PricingProxy pricingProxy;

    @Inject
    private PaymentProxy paymentProxy;

    @Inject
    private CartProxy cartProxy;

    @Inject
    private OrderProxy orderProxy;



    public List<Pet> getAvailablePets(String token) {
        System.out.println("IDZIE GET AVAILABLE PETS");
        List<Pet> pets = new ArrayList<>();
        for(Item item: catalogProxy.getAllItems()) {
            System.out.println("ZARAZ POJDZIE CALL DO PROXY");
            Price price = pricingProxy.getPrice(item.getName(), token);

            Pet pet = new Pet();
            pet.setItemId(item.getItemId());
            pet.setName(item.getName());
            pet.setPrice(price.getPrice());
            pet.setQuantity(item.getQuantity());
            pet.setDescription(item.getDescription());

            pets.add(pet);
        }
        return pets;
    }

    public void addToCart(String customerId, CartItem item){
        cartProxy.addToCart(customerId, item);
    }

    public List<CartItem> getCart(String customerId){
        return cartProxy.getCart(customerId);
    }

    public String buy(int customerId){
//        Cart cart = cartProxy.getCart(customerId);
//
//        Order order = createOrderFromCart(customerId, cart);
//        int orderId  = orderProxy.createOrder(order);
//
//        Payment payment = new Payment();
//        payment.setMerchantId(Constants.MERCHANT_ID);
//        payment.setDescription(String.format("ORDER_ID: %s", orderId));
//        payment.setAmount(order.getPrice());
//
//        Response response =  paymentProxy.createPayment(payment);
//
//        if(response.getStatus() == Response.Status.SERVICE_UNAVAILABLE.getStatusCode()){
//            throw new RuntimeException("Payment service unreachable");
//        }
//
//        return (String) response.readEntity(String.class);
        return "pies";
    }

    private Order createOrderFromCart(int customerId, Cart cart){
        Order order = new Order();
        order.setCustomerId(customerId);
        for(Cart.Item ci : cart.getItems()){
            order.getItems().add(new Order.Item(order, ci.getItemId(),ci.getQuantity()));
        }
        order.setPrice(cart.calculatePrice());
        return order;
    }


}
