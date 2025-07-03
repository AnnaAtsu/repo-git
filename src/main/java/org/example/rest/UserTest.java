package org.example.rest;

import com.github.javafaker.Faker;
import org.example.client.SpecConfig;
import org.example.dto.AdminRequest;
import org.example.dto.UserRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static io.restassured.RestAssured.given;

public class UserTest {

    UserRequest request;

    @BeforeEach
    void setup() {
        Faker faker = new Faker();

        request = new UserRequest(
                // mode - случайный выбор между значениями
                faker.options().option("marriage", "birth", "death", "name_change"),

                // Личные данные
                faker.name().lastName(),
                faker.name().firstName(),
                faker.phoneNumber().phoneNumber(),
                faker.number().digits(10), // Номер паспорта
                faker.idNumber().valid(),

                // Данные гражданина
                faker.name().lastName(),
                faker.name().firstName(),
                faker.name().firstName(), // Отчество
                faker.number().digits(2),
                faker.number().digits(10), // Номер паспорта гражданина
                faker.options().option("male", "female"),

                // Дата брака (если mode = marriage)
                faker.number().digits(2),

                // Новые данные при смене фамилии
                faker.name().lastName(),

                // Данные другого человека
                faker.name().lastName(),
                faker.name().firstName(),
                faker.name().firstName(), // Отчество
                faker.number().digits(2),
                faker.number().digits(10), // Паспорт другого человека

                // Место рождения
                faker.address().cityName() + ", " + faker.address().streetAddress(),

                // Данные родителей
                faker.name().fullName(), // Мать
                faker.name().fullName(), // Отец

                // Данные о смерти
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
                .spec(SpecConfig.responseSpecification());
    }
}
