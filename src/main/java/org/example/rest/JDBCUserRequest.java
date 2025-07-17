package org.example.rest;

import com.github.javafaker.Faker;
import org.example.client.SpecConfig;
import org.example.dto.UserRequest;
import org.example.jdbc.JDBCpostgresql;
import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class JDBCUserRequest {

    private JDBCpostgresql dbConnector;
    UserRequest request;

    @BeforeEach
    public void setUpDB() throws SQLException {
        // Инициализация подключения к БД
        dbConnector = new JDBCpostgresql();
        dbConnector.connect();
    }

    @AfterEach
    public void tearDown() {
        // Закрываем соединение с БД
        if (dbConnector != null) {
            dbConnector.disconnect();
        }
    }


    @BeforeEach
    void setup() {
        Faker faker = new Faker();

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
    public void JDBCgetUserRequest() {
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
        try (ResultSet result = dbConnector.executeQuery(
                "SELECT * FROM reg_office.citizens WHERE surname = ? AND name = ? AND middlename = ?",
                request.citizenLastName(), request.citizenFirstName(), request.citizenMiddleName())) {

            Assertions.assertTrue(result.next(), "Нет данных в результате запроса");

            String surname = result.getString("surname");
            String name = result.getString("name");
            String middlename = result.getString("middlename");

            System.out.println(surname + " " + name + " " + middlename);

            Assertions.assertEquals(request.citizenLastName(), surname);
            Assertions.assertEquals(request.citizenFirstName(), name);
            Assertions.assertEquals(request.citizenMiddleName(), middlename);
            assertAll("Проверка данных юзера",
                    () -> assertNotNull(result.getString("citizenid")),
                    () -> assertNotNull(result.getString("passportnumber")),
                    () -> assertNotNull(result.getString("gender")),
                    () -> assertNotNull(result.getString("registration_address")),
                    () -> assertNotNull(result.getTimestamp("dateofbirth")),
                    () -> assertNull(result.getString("image"))
            );

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
