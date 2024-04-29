package dev.project.fight.resource;

import dev.project.fight.entity.Fight;
import dev.project.fight.entity.Fighters;
import dev.project.fight.service.FightService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import java.util.List;
import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/api/fight")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FightResource {

//    private static final Logger LOGGER = Logger.getLogger(FightResource.class);

    @Inject
    FightService service;

    @Operation(summary = "Returns two random fighters")
    @APIResponse(responseCode = "200",
            content = @Content(mediaType = APPLICATION_JSON,
                    schema = @Schema(implementation = Fighters.class, required = true)))
    @GET
    @Path("/randomfighters")
    public Response getRandomFighters() throws InterruptedException {
        Fighters fighters = service.findRandomFighters();
//        LOGGER.debug("Get random fighters " + fighters);
        return Response.ok(fighters).build();
    }

    @Operation(summary = "Returns all the fights from the database")
    @APIResponse(responseCode = "200",
            content = @Content(mediaType = APPLICATION_JSON,
                    schema = @Schema(implementation = Fight.class, type = SchemaType.ARRAY)))
    @APIResponse(responseCode = "204", description = "No fights")
    @GET
    public Response getAllFights() {
        List<Fight> fights = service.findAllFights();
//        LOGGER.debug("Total number of fights " + fights);
        return Response.ok(fights).build();
    }

    @Operation(summary = "Returns a fight for a given identifier")
    @APIResponse(responseCode = "200",
            content = @Content(mediaType = APPLICATION_JSON,
                    schema = @Schema(implementation = Fight.class)))
    @APIResponse(responseCode = "204", description = "The fight is not found for a given identifier")
    @GET
    @Path("/{id}")
    public Response getFight(@Parameter(description = "Fight identifier", required = true) @PathParam("id") Long id) {
        Fight fight = service.findFightById(id);
        if (fight != null) {
//            LOGGER.debug("Found fight " + fight);
            return Response.ok(fight).build();
        } else {
//            LOGGER.debug("No fight found with id " + id);
            return Response.noContent().build();
        }
    }

    @Operation(summary = "Trigger a fight between two fighters")
    @APIResponse(responseCode = "200",
            description = "The result of the fight",
            content = @Content(mediaType = APPLICATION_JSON,
                    schema = @Schema(implementation = Fight.class)))
    @POST
    public Fight fight(
            @RequestBody(description = "The two fighters fighting",
                    required = true, content = @Content(mediaType = APPLICATION_JSON,
                    schema = @Schema(implementation = Fighters.class))) @Valid Fighters fighters,
            @Context UriInfo uriInfo) {
        return service.persistFight(fighters);
    }

}