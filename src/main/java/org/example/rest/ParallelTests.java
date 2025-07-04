package org.example.rest;

import org.example.client.SpecConfig;
import org.example.dto.GetApplicationResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.util.List;
import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Execution(ExecutionMode.CONCURRENT)
public class ParallelTests {


    @Test
    void test1() {
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

    @Test
    void test2() {
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
