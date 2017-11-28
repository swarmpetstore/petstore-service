package org.packt.swarm.petstore;

import org.packt.swarm.petstore.model.Pet;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/")
public class PetstoreResource {

    @Inject
    private PetstoreService petstoreService;

    @GET
    @Path("pet")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Pet> getAvailablePets() {
        return petstoreService.getAvailablePets();
    }


    @GET
    @Path("pay")
    @Produces(MediaType.APPLICATION_JSON)
    public Response pay(@QueryParam("customerId") int customerId){
        petstoreService.pay(customerId);
        return Response.ok().build();
    }



}
