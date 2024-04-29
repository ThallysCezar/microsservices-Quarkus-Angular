package dev.project.fight.client;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/api/villains")
@Produces(MediaType.APPLICATION_JSON)
@RegisterRestClient(baseUri="http://localhost:8084")
public interface VillainService {

    @GET
    @Path("/random")
    Villain findRandomVillain();
}