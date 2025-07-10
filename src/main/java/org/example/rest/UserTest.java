package org.example.rest;

import com.github.javafaker.Faker;
import org.example.client.SpecConfig;

import org.example.dto.UserRequest;
import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static io.restassured.RestAssured.given;

public class UserTest {

    UserRequest request;

    @BeforeEach
    void setup() {
        Faker faker = new Faker();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        request = new UserRequest(
                "wedding",  // mode
                faker.name().lastName(),      // personalLastName
                faker.name().firstName(),     // personalFirstName
                faker.name().firstName(), // personalMiddleName
                "12312312123", // personalPhoneNumber
                "23123123",   // personalNumberOfPassport
                faker.address().streetAddress(), // personalAddress
                faker.name().lastName(), // citizenLastName
                faker.name().firstName(), // citizenFirstName
                faker.name().firstName(), // citizenMiddleName
                LocalDate.now().toString(), // citizenBirthDate
                "1231232",   // citizenNumberOfPassport
                faker.options().option("male", "female"),      // citizenGender
                faker.address().streetAddress(), // citizenAddress
                LocalDate.ofYearDay(2000, 12).toString(), // dateOfMarriage
                faker.funnyName().name(), // newLastName
                faker.name().firstName(), // anotherPersonLastName
                faker.name().firstName(), // anotherPersonFirstName
                faker.name().firstName(), // anotherPersonMiddleName
                LocalDate.ofYearDay(2002, 20).toString(), // birth_of_anotoherPerson
                faker.name().firstName(),  // anotherPersonPassport
                null,        // birth_place
                null,        // birth_mother
                null,        // birth_father
                null,        // birth_grandpa
                null,        // birth_grandma
                null,        // death_dateOfDeath
                null
        );

    }




    @Test
    void sendAUserRequestTest() {

        given()
                .spec(SpecConfig.requestSpecification())
                .basePath("/sendUserRequest")
                .body(request)
                .when()
                .post()
                .then()
                .statusCode(200)
                .log().body()
                .header("Content-Type", IsEqual.equalTo("application/json; charset=utf-8"))
                .spec(SpecConfig.responseSpecification());


    }
}
