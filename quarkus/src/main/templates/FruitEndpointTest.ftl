package com.test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

import com.test.entity.Fruit${no};
import com.test.resource.FruitResource${no};
import io.quarkus.test.common.http.TestHTTPEndpoint;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@TestHTTPEndpoint(FruitResource${no}.class)
public class FruitEndpointTest${no} {

    @Test
    public void testFruitEndpoint${no}() {

        //Create the Pear:
        given()
                .when()
                .body("{\"name\" : \"Pear\"}")
                .contentType("application/json")
                .post()
                .then()
                .statusCode(201);
        int id = given()
                .when().get()
                .then()
                .statusCode(200)
                .body(
                        containsString("Pear"))
                .extract().body().as(Fruit${no}[].class)[0].getId();

        given()
                .when().delete("" + id)
                .then()
                .statusCode(204);

    }

}
