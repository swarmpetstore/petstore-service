package org.packt.swarm.petstore;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class PetstoreResource {

    @Inject
    private PetstoreService petstoreService;

    @GET
    @Path("pet")
    @Produces(MediaType.APPLICATION_JSON)
    public Pet searchById() {
        return petstoreService.getAvailablePets();
    }

}
