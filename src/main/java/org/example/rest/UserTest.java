package org.example.rest;

import com.github.javafaker.Faker;
import org.example.client.SpecConfig;
import org.example.dto.AdminRequest;
import org.example.dto.UserRequest;
import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Optional;

import static com.google.common.base.Predicates.equalTo;
import static io.restassured.RestAssured.given;

public class UserTest {

    UserRequest request;

    @BeforeEach
    void setup() {
        Faker faker = new Faker();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        request = new UserRequest(
                faker.options().option("marriage","name_change"),
                 faker.name().lastName(),
                faker.name().firstName(),
                "+7" + faker.phoneNumber().subscriberNumber(10),
                faker.number().digits(10), // Номер паспорта
                 // Данные гражданина
                faker.name().lastName(),
                faker.name().firstName(),
                faker.name().firstName(),
                LocalDate.now()
                        .minusYears(faker.number().numberBetween(18, 80))
                        .format(dateFormatter),
                faker.number().digits(10), // Номер паспорта гражданина
                faker.options().option("male", "female"),
                // Дата брака
                LocalDate.now().format(dateFormatter),
                faker.funnyName().name(),
                faker.name().lastName(),
                // Данные другого человека
                faker.name().lastName(),
                faker.name().firstName(),
                LocalDate.now()
                        .minusYears(faker.number().numberBetween(18, 80))
                        .format(dateFormatter),
                faker.number().digits(10),
                // Место рождения
                 faker.address().cityName() + ", " + faker.address().streetAddress(),
                // Данные родителей
                faker.name().fullName(), // Мать
                faker.name().fullName(), // Отец
                LocalDate.now()
                        .minusDays(faker.number().numberBetween(1, 365))
                        .format(dateFormatter),
                faker.address().cityName() + " больница"
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
                .header("Content-Type", IsEqual.equalTo("application/json; charset=utf-8"))
                .spec(SpecConfig.responseSpecification());


    }
}
