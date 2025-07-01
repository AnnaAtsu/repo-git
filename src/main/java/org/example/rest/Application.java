package org.example.rest;

import org.example.client.SpecConfig;
import org.example.dto.GetApplicationResponse;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class Application {

    @Test
    public void getApplications() {
        GetApplicationResponse response =
        given()
        .spec(SpecConfig.requestSpecification())
                .basePath("/getApplications")
                .when()
                .get()
                .then()
                .spec(SpecConfig.responseSpecification())
                .statusCode(201)
                .extract().as(GetApplicationResponse.class);

        assertNotNull(response.getClass()); //getTotal()
    }
}
