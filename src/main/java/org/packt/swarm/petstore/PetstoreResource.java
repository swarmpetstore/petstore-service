package org.packt.swarm.petstore;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.packt.swarm.petstore.model.CartItem;
import org.packt.swarm.petstore.model.Pet;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.LinkedList;
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
        List<Pet> result = petstoreService.getAvailablePets(token);
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
    public Response addToCart(@PathParam("customerId") String customerId, CartItem item) {
        try {
            petstoreService.addToCart(customerId, item);
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
    public Response addToCart(@PathParam("customerId") String customerId) {
        try {
            List<CartItem> cart = petstoreService.getCart(customerId);
            return Response.ok(cart).build();
        } catch (Exception e) {
            System.out.println("WYCHRZANILO SIE");
            e.printStackTrace();
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
