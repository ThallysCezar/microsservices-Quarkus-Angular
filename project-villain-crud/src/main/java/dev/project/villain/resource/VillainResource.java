package dev.project.villain.resource;

import dev.project.villain.entity.Villain;
import dev.project.villain.service.VillainService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import java.net.URI;
import java.util.List;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/api/villains")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VillainResource {

//    private static final Logger LOGGER = Logger.getLogger(HeroResource.class);

    @Inject
    VillainService service;

    @Operation(summary = "Returns a random villain")
    @APIResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Villain.class, required = true)))
    @GET
    @Path("/random")
    public Response getRandomVillain() {
        Villain villain = service.findRandomVillain();
//        LOGGER.debug("Found random villain " + villain);
        return Response.ok(villain).build();
    }

    @Operation(summary = "Returns all the villains from the database")
    @APIResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Villain.class, type = SchemaType.ARRAY)))
    @APIResponse(responseCode = "204", description = "No villains")
    @GET
    public Response getAllVillains() {
        List<Villain> villains = service.findAllVillains();
//      LOGGER.debug("Total number of villains " + villains);
        return Response.ok(villains).build();
    }

    @Operation(summary = "Returns a villain for a given identifier")
    @APIResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Villain.class)))
    @APIResponse(responseCode = "204", description = "The villain is not found for a given identifier")
    @GET
    @Path("/{id}")
    public Response getHero(@PathParam("id") Long id) {
        Villain villain = service.findVillainById(id);
        if (villain != null) {
//          LOGGER.debug("Found villain " + villain);
            return Response.ok(villain).build();
        } else {
//          LOGGER.debug("No villain found with id " + id);
            return Response.noContent().build();
        }
    }

    @Operation(summary = "Creates a valid villain")
    @APIResponse(responseCode = "201", description = "The URI of the created villain", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = URI.class)))
    @POST
    public Response createVillain(
            @Valid Villain villain, @Context UriInfo uriInfo) {
        villain = service.persistVillain(villain);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(Long.toString(villain.id));
//        LOGGER.debug("New villain created with URI " + builder.build().toString());
        return Response.created(builder.build()).build();
    }

    @Operation(summary = "Updates an exiting  villain") @APIResponse(responseCode = "200", description = "The updated villain", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Villain.class)))
    @PUT public Response updateHero(@Valid Villain villain) {
        villain = service.updateVillain(villain);
//        LOGGER.debug("villain updated with new valued " + villain);
        return Response.ok(villain).build();
    }

    @Operation(summary = "Deletes an exiting villain")
    @APIResponse(responseCode = "204")
    @DELETE
    @Path("/{id}")
    public Response deleteVillain(@PathParam("id") Long id) {
        service.deleteVillain(id);
//        LOGGER.debug("villain deleted with " + id);
        return Response.noContent().build();
    }
}
