package dev.project.villains;

import dev.project.villain.entity.Villain;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.common.mapper.TypeRef;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.List;
import java.util.Random;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static jakarta.ws.rs.core.HttpHeaders.ACCEPT;
import static jakarta.ws.rs.core.HttpHeaders.CONTENT_TYPE;
import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static jakarta.ws.rs.core.Response.Status.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class VillainResourceTest {

    private static final String DEFAULT_NAME = "Super Baguette";
    private static final String UPDATED_NAME = "Super Baguette (updated)";
    private static final String DEFAULT_OTHER_NAME = "Super Baguette Tradition";
    private static final String UPDATED_OTHER_NAME = "Super Baguette Tradition (updated)";
    private static final String DEFAULT_PICTURE = "super_baguette.png";
    private static final String UPDATED_PICTURE = "super_baguette_updated.png";
    private static final String DEFAULT_POWERS = "eats baguette really quickly";
    private static final String UPDATED_POWERS = "eats baguette really quickly (updated)";
    private static final int DEFAULT_LEVEL = 42;
    private static final int UPDATED_LEVEL = 43;

    private static final int NB_VILLAINS = 3;
    private static String heroId;

    @Test
    void shouldNotGetUnknownHero() {
        Long randomId = new Random().nextLong();
        given()
                .pathParam("id", randomId)
                .when().get("/api/villains/{id}")
                .then()
                .statusCode(NO_CONTENT.getStatusCode());
    }

    @Test
    void shouldGetRandomHero(){
        given()
                .when().get("/api/villains/random")
                .then()
                .statusCode(OK.getStatusCode())
                .header(CONTENT_TYPE, APPLICATION_JSON);
    }

    @Test
    void shouldNotAddInvalidItem(){
        Villain villain = new Villain();
        villain.name = null;
        villain.otherName = DEFAULT_OTHER_NAME;
        villain.picture = DEFAULT_PICTURE;
        villain.powers = DEFAULT_POWERS;
        villain.level = 0;

        given()
                .body(villain)
                .header(CONTENT_TYPE, APPLICATION_JSON)
                .header(ACCEPT, APPLICATION_JSON)
                .when().post("/api/villains")
                .then()
                .statusCode(BAD_REQUEST.getStatusCode());
    }

    @Test
    @Order(1)
    void shouldGetInitialItems() {
        List<Villain> villains = get("/api/villains").then()
                .statusCode(OK.getStatusCode())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .extract().body().as(getVillainTypeRef());
        assertEquals(NB_VILLAINS, villains.size());
    }

//    @Test
//    @Order(2)
//    void shouldAddAnItem() {
//        villain villain = new villain();
//        villain.id = 4L;
//        villain.name = DEFAULT_NAME;
//        villain.otherName = DEFAULT_OTHER_NAME;
//        villain.picture = DEFAULT_PICTURE;
//        villain.powers = DEFAULT_POWERS;
//        villain.level = DEFAULT_LEVEL;
//
//        String location = given()
//                .body(villain)
//                .header(CONTENT_TYPE, APPLICATION_JSON)
//                .header(ACCEPT, APPLICATION_JSON)
//                .when()
//                .post("/api/villains")
//                .then()
//                .statusCode(CREATED.getStatusCode())
//                .extract().header("Location");
//        assertTrue(location.contains("/api/villains"));
//
////        // Stores the id
////        String[] segments = location.split("/");
////        heroId = segments[segments.length - 1];
////        assertNotNull(heroId);
//
//        given()
//                .pathParam("id", 4)
//                .when().get("/api/villains/{id}")
//                .then()
//                .statusCode(OK.getStatusCode())
//                .header(CONTENT_TYPE, APPLICATION_JSON)
//                .body("name", Is.is(DEFAULT_NAME))
//                .body("otherName", Is.is(DEFAULT_OTHER_NAME))
//                .body("level", Is.is(DEFAULT_LEVEL))
//                .body("picture", Is.is(DEFAULT_PICTURE))
//                .body("powers", Is.is(DEFAULT_POWERS));
//
//        List<villain> villains = get("/api/villains").then()
//                .statusCode(OK.getStatusCode())
//                .header(CONTENT_TYPE, APPLICATION_JSON)
//                .extract().body().as(getHeroTypeRef());
//        assertEquals(NB_HEROES + 1, villains.size());
//    }

    @Test
    @Order(3)
    void shouldUpdateAnItem() {
        Villain villain = new Villain();
        villain.id = 2L;
        villain.name = UPDATED_NAME;
        villain.otherName = UPDATED_OTHER_NAME;
        villain.picture = UPDATED_PICTURE;
        villain.powers = UPDATED_POWERS;
        villain.level = UPDATED_LEVEL;

        given()
                .body(villain)
                .header(CONTENT_TYPE, APPLICATION_JSON)
                .header(ACCEPT, APPLICATION_JSON)
                .when()
                .put("/api/villains")
                .then()
                .statusCode(OK.getStatusCode())
                .header(CONTENT_TYPE, APPLICATION_JSON)
                .body("name", Is.is(UPDATED_NAME))
                .body("otherName", Is.is(UPDATED_OTHER_NAME))
                .body("level", Is.is(UPDATED_LEVEL))
                .body("picture", Is.is(UPDATED_PICTURE))
                .body("powers", Is.is(UPDATED_POWERS));

        List<Villain> villains = get("/api/villains").then()
                .statusCode(OK.getStatusCode())
                .header(CONTENT_TYPE, APPLICATION_JSON)
                .extract().body().as(getVillainTypeRef());
        assertEquals(NB_VILLAINS, villains.size());
    }


    @Test
    @Order(4)
    void shouldRemoveAnItem(){
        given()
                .pathParam("id", 1)
                .when().delete("/api/villains/{id}")
                .then()
                .statusCode(NO_CONTENT.getStatusCode());

        List<Villain> villains = get("/api/villains").then()
                .statusCode(OK.getStatusCode())
                .header(CONTENT_TYPE, APPLICATION_JSON)
                .extract().body().as(getVillainTypeRef());
        assertEquals(NB_VILLAINS - 1, villains.size());
    }

//    @Test
//    void shouldPingOpenAPI() {
//        given()
//                .header(ACCEPT, APPLICATION_JSON)
//                .when().get("/q/openapi")
//                .then()
//                .statusCode(OK.getStatusCode());
//    }
//
//    @Test
//    void shouldPingSwaggerUI() {
//        given()
//                .when().get("/q/swagger-ui")
//                .then()
//                .statusCode(OK.getStatusCode());
//    }

    private TypeRef<List<Villain>> getVillainTypeRef() {
        return new TypeRef<List<Villain>>() {
            // Kept empty on purpose
        };
    }

}