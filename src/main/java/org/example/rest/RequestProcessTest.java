package org.example.rest;

import com.github.javafaker.Faker;
import org.example.client.SpecConfig;
import org.example.dto.AdminRequest;
import org.example.dto.ProcessRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.request;
import static org.hamcrest.core.IsEqual.equalTo;

public class RequestProcessTest {
    ProcessRequest request1;

    @BeforeEach
    void setup() {
        Faker faker = new Faker();
        request1 = new ProcessRequest(
                Integer.valueOf(faker.options().option("51609", "51607","51602", "51600")),
                null,  // Явное указание null
                faker.options().option("approve", "reject")
        );
    }






    @Test
    void RequestProcessTest() {
        given()
                .spec(SpecConfig.requestSpecification())
                .basePath("/requestProcess")
                .body(request1)
                .when()
                .post()
                .then()
                .statusCode(200)
                .header("Content-Type", equalTo("application/json; charset=utf-8"))
                .body("staffid", equalTo(null))
                .spec(SpecConfig.responseSpecification());
    }
}
