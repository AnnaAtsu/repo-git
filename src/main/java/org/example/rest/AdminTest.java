package org.example.rest;

import com.github.javafaker.Faker;
import org.example.client.SpecConfig;
import org.example.dto.AdminRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class AdminTest {
    AdminRequest request;

    @BeforeEach
    void setup() {
        Faker faker = new Faker();
        request = new AdminRequest(
                faker.name().firstName(),
                faker.name().lastName(),
                faker.funnyName().name(),
                faker.number().digits(9),
                faker.number().digits(8),
                LocalDate.now().toString()
        );
    }

    @Test
    void sendAdminRequestTest() {
        given()
                .spec(SpecConfig.requestSpecification())
                .basePath("/sendAdminRequest")
                .body(request)
                .when()
                .post()
                .then()
                .statusCode(200)
                .header("Content-Type", equalTo("application/json; charset=utf-8"))
                .spec(SpecConfig.responseSpecification());
    }

}
