package org.packt.swarm.petstore;

import org.keycloak.KeycloakPrincipal;
import org.packt.swarm.petstore.api.CartItemView;
import org.packt.swarm.petstore.api.CatalogItemView;
import org.packt.swarm.petstore.cart.api.CartItem;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.List;

@Path("/")
public class PetstoreResource {

    @Inject
    private PetstoreService petstoreService;

    @GET
    @Path("/pet")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAvailablePets(@Context SecurityContext securityContext) {
        try {

            KeycloakPrincipal keycloakPrincipal = (KeycloakPrincipal) securityContext.getUserPrincipal();

        String token = null;
        if(keycloakPrincipal != null && keycloakPrincipal.getKeycloakSecurityContext()!=null) {
            token = keycloakPrincipal.getKeycloakSecurityContext().getTokenString();
        }
        List<CatalogItemView> result = petstoreService.getAvailablePets(token);
        return Response.ok(result).build();
        } catch (Exception e) {
            System.out.println("WYCHRZANILO SIE");
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @POST
    @Path("/cart/{customerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addToCart(@PathParam("customerId") String customerId, CartItem item, @QueryParam("additive") boolean additive) {
        try {
            petstoreService.addToCart(customerId, item, additive);
            return Response.ok().build();
        } catch (Exception e) {
            System.out.println("WYCHRZANILO SIE");
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/cart/{customerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCart(@PathParam("customerId") String customerId) {
        try {
            List<CartItemView> cart = petstoreService.getCart(customerId);
            return Response.ok(cart).build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @DELETE
    @Path("/cart/{customerId}/{itemId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteFromCart(@PathParam("customerId") String customerId, @PathParam("itemId") String itemId) {
        try {
            petstoreService.deleteFromCart(customerId, itemId);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }


    @POST
    @Path("buy")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buy(@QueryParam("customerId") int customerId, @Context  SecurityContext securityContext){
        try {
            System.out.println("PRINCIPAL TO "+securityContext.getUserPrincipal().getName());
            if(securityContext.isUserInRole("client")){
                System.out.println("PRINCIPAL JEST KLIENTEM");
            } else {
                System.out.println("KLIENTEM KWA TO NIE JEST");
            }
            if(securityContext.isUserInRole("admin")){
                System.out.println("PRINCIPAL JEST ADMINEM");
            } else {
                System.out.println("ADMINEM KWA TO NIE JEST");
            }
            String paymentUUID = petstoreService.buy(customerId);
            return Response.ok(paymentUUID).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

}
