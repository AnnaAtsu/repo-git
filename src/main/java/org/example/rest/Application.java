package org.example.rest;

import org.example.client.SpecConfig;
import org.example.dto.GetApplicationResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.Random;

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
                .statusCode(200)
                .extract().as(GetApplicationResponse.class);

        assertNotNull(response.getTotal());
    }




    //сделать с параметрами
    @ParameterizedTest
    @ValueSource(ints = {51524, 51577, 51596, 51595})
    public void getApplStatus(int applicationId) {
        String url = "/getApplStatus/" + applicationId;
        GetApplicationResponse response =
                given()
                        .spec(SpecConfig.requestSpecification())
                        .basePath(url)
                        .when()
                        .get()
                        .then()
                        .spec(SpecConfig.responseSpecification())
                        .statusCode(200)
                        .extract().as(GetApplicationResponse.class);
       assertNotNull(response.getRequestId());
        assertNotNull(response.getData());
    }
    //сделать с параметрами
    @Test
    public void getApplStatusFromList() {
        List<Integer> applicationIds = List.of(51590, 51547, 51593, 51592);
        int applicationId = applicationIds.get(new Random().nextInt(applicationIds.size()));
       String url = "/getApplStatus/" + applicationId;
        GetApplicationResponse response =
                given()
                        .spec(SpecConfig.requestSpecification())
                        .basePath(url)
                        .when()
                        .get()
                        .then()
                        .spec(SpecConfig.responseSpecification())
                        .statusCode(200)
                        .extract().as(GetApplicationResponse.class);
        assertNotNull(response.getRequestId());
        assertNotNull(response.getData());
    }


}
