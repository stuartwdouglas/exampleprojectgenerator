package com.test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

import com.test.entity.Fruit;
import com.test.resource.FruitResource;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@TestHTTPEndpoint(FruitResource.class)
public class FruitsEndpointTest {

    @Test
    public void testFruitEndpoint() {

        //Create the Pear:
        given()
                .when()
                .body("{\"name\" : \"Cherry\"}")
                .contentType("application/json")
                .post()
                .then()
                .statusCode(201);
        int id = given()
                .when().get()
                .then()
                .statusCode(200)
                .body(
                        containsString("Cherry"))
                .extract().body().as(Fruit[].class)[0].getId();

        given()
                .when().delete("" + id)
                .then()
                .statusCode(204);

    }

}
