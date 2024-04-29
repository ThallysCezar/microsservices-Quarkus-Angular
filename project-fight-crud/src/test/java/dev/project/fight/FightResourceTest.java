package dev.project.fight;

import dev.project.fight.client.Hero;
import dev.project.fight.client.Villain;
import dev.project.fight.entity.Fight;
import dev.project.fight.entity.Fighters;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.common.mapper.TypeRef;
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
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FightResourceTest {

    private static final String DEFAULT_WINNER_NAME = "Super Baguette";
    private static final String DEFAULT_WINNER_PICTURE = "super_baguette.png";
    private static final int DEFAULT_WINNER_LEVEL = 42;
    private static final String DEFAULT_LOSER_NAME = "Super Chocolatine";
    private static final String DEFAULT_LOSER_PICTURE = "super_chocolatine.png";
    private static final int DEFAULT_LOSER_LEVEL = 6;

    private static final int NB_FIGHTS = 4;
    private static String fightId;

    @Test
    void shouldPingOpenAPI() {
        given()
                .header(ACCEPT, APPLICATION_JSON)
                .when().get("/q/openapi")
                .then()
                .statusCode(OK.getStatusCode());
    }

    @Test
    void shouldPingSwaggerUI() {
        given()
                .when().get("/q/swagger-ui")
                .then()
                .statusCode(OK.getStatusCode());
    }


    @Test
    void shouldNotGetUnknownFight() {
        Long nonExistentId = 999L;
        given()
                .pathParam("id", nonExistentId)
                .when().get("/api/fight/{id}")
                .then()
                .statusCode(NOT_FOUND.getStatusCode());
    }


    @Test
    void shouldNotAddInvalidItem() {
        Fighters fighters = new Fighters();
        fighters.hero = null;
        fighters.villain = null;

        given()
                .body(fighters)
                .header(CONTENT_TYPE, APPLICATION_JSON)
                .header(ACCEPT, APPLICATION_JSON)
                .when()
                .post("/api/fight")
                .then()
                .statusCode(NOT_FOUND.getStatusCode());
    }

    @Test
    @Order(1)
    void shouldGetInitialItems() {
        List<Fight> fights = get("/api/fight").then()
                .statusCode(OK.getStatusCode())
                .header(CONTENT_TYPE, APPLICATION_JSON)
                .extract().body().as(getFightTypeRef());
        assertEquals(NB_FIGHTS, fights.size());
    }

    @Test
    @Order(2)
    void shouldAddAnItem() {
        Hero hero = new Hero();
        hero.name = DEFAULT_WINNER_NAME;
        hero.picture = DEFAULT_WINNER_PICTURE;
        hero.level = DEFAULT_WINNER_LEVEL;
        Villain villain = new Villain();
        villain.name = DEFAULT_LOSER_NAME;
        villain.picture = DEFAULT_LOSER_PICTURE;
        villain.level = DEFAULT_LOSER_LEVEL;
        Fighters fighters = new Fighters();
        fighters.hero = hero;
        fighters.villain = villain;

        fightId = given()
                .body(fighters)
                .header(CONTENT_TYPE, APPLICATION_JSON)
                .header(ACCEPT, APPLICATION_JSON)
                .when()
                .post("/api/fight")
                .then()
                .statusCode(OK.getStatusCode())
                .body(containsString("winner"), containsString("loser"))
                .extract().body().jsonPath().getString("id");

        assertNotNull(fightId);

        given()
                .pathParam("id", fightId)
                .when().get("/api/fight/{id}")
                .then()
                .statusCode(OK.getStatusCode())
                .header(CONTENT_TYPE, APPLICATION_JSON)
                .body("winnerName", is(DEFAULT_WINNER_NAME))
                .body("winnerPicture", is(DEFAULT_WINNER_PICTURE))
                .body("winnerLevel", is(DEFAULT_WINNER_LEVEL))
                .body("loserName", is(DEFAULT_LOSER_NAME))
                .body("loserPicture", is(DEFAULT_LOSER_PICTURE))
                .body("loserLevel", is(DEFAULT_LOSER_LEVEL))
                .body("fightDate", is(notNullValue()));

        List<Fight> fights = get("/api/fights").then()
                .statusCode(OK.getStatusCode())
                .header(CONTENT_TYPE, APPLICATION_JSON)
                .extract().body().as(getFightTypeRef());
        assertEquals(NB_FIGHTS + 1, fights.size());
    }

    private TypeRef<List<Fight>> getFightTypeRef() {
        return new TypeRef<List<Fight>>() {
            // Kept empty on purpose
        };
    }

}