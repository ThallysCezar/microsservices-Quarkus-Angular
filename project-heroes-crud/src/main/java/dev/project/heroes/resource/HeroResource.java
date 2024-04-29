package dev.project.heroes.resource;

import dev.project.heroes.entity.Hero;
import dev.project.heroes.service.HeroService;
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

@Path("/api/heroes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HeroResource {

//    private static final Logger LOGGER = Logger.getLogger(HeroResource.class);

    @Inject
    HeroService service;

    @Operation(summary = "Returns a random hero")
    @APIResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Hero.class, required = true)))
    @GET
    @Path("/random")
    public Response getRandomHero() {
        Hero hero = service.findRandomHero();
//        LOGGER.debug("Found random hero " + hero);
        return Response.ok(hero).build();
    }

    @Operation(summary = "Returns all the heroes from the database")
    @APIResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Hero.class, type = SchemaType.ARRAY)))
    @APIResponse(responseCode = "204", description = "No heroes")
    @GET
    public Response getAllHeroes() {
        List<Hero> heroes = service.findAllHeroes();
//      LOGGER.debug("Total number of heroes " + heroes);
        return Response.ok(heroes).build();
    }

    @Operation(summary = "Returns a hero for a given identifier")
    @APIResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Hero.class)))
    @APIResponse(responseCode = "204", description = "The hero is not found for a given identifier")
    @GET
    @Path("/{id}")
    public Response getHero(@PathParam("id") Long id) {
        Hero hero = service.findHeroById(id);
        if (hero != null) {
//          LOGGER.debug("Found hero " + hero);
            return Response.ok(hero).build();
        } else {
//          LOGGER.debug("No hero found with id " + id);
            return Response.noContent().build();
        }
    }

    @Operation(summary = "Creates a valid hero")
    @APIResponse(responseCode = "201", description = "The URI of the created hero", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = URI.class)))
    @POST
    public Response createHero(
            @Valid Hero hero, @Context UriInfo uriInfo) {
        hero = service.persistHero(hero);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(Long.toString(hero.id));
//        LOGGER.debug("New hero created with URI " + builder.build().toString());
        return Response.created(builder.build()).build();
    }

    @Operation(summary = "Updates an exiting  hero")
    @APIResponse(responseCode = "200", description = "The updated hero", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Hero.class)))
    @PUT
    public Response updateHero(@Valid Hero hero) {
        hero = service.updateHero(hero);
//        LOGGER.debug("Hero updated with new valued " + hero);
        return Response.ok(hero).build();
    }

    @Operation(summary = "Deletes an exiting hero")
    @APIResponse(responseCode = "204")
    @DELETE
    @Path("/{id}")
    public Response deleteHero(@PathParam("id") Long id) {
        service.deleteHero(id);
//        LOGGER.debug("Hero deleted with " + id);
        return Response.noContent().build();
    }
}
