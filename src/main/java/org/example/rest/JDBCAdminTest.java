package org.example.rest;

import com.github.javafaker.Faker;
import org.example.client.SpecConfig;
import org.example.dto.AdminRequest;
import org.example.jdbc.JDBCpostgresql;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.*;

public class JDBCAdminTest {
    private String firstName;
    private String lastName;
    private String funnyName;
    private String digits9;
    private String digits8;
    private String currentDate;
    private JDBCpostgresql dbConnector;
    AdminRequest request;


    @BeforeEach
    void setup() {
        Faker faker = new Faker();

        this.firstName = faker.name().firstName();
        this.lastName = faker.name().lastName();
        this.funnyName = faker.funnyName().name();
        this.digits9 = faker.number().digits(9);
        this.digits8 = faker.number().digits(8);
        this.currentDate = LocalDate.now().toString();

        this.request = new AdminRequest(
                firstName,
                lastName,
                funnyName,
                digits9,
                digits8,
                currentDate
        );
    }

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

    @Test
    public void JDBCgetAdminRequest() throws SQLException {

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

        try (ResultSet result = dbConnector.executeQuery(
                "SELECT * FROM reg_office.staff WHERE surname = '" + lastName + "' "))
        {

            Assertions.assertTrue(result.next(), "Нет данных в результате запроса");
            assertEquals(lastName, result.getString("surname"));
           // assertEquals(firstName, result.getString("name"), "name is null");
            assertNotNull(result.getTimestamp("dateofbirth"), "Creation date is null");
            assertNotNull(result.getString("passportnumber"), "passportnumber is null");
            assertNotNull(result.getString("phonenumber"), "phonenumber is null");
            assertNotNull(result.getString("name"), "name is null");
            assertNotNull(result.getString("middlename"), "middlename is null");
            assertNotNull(result.getString("staffid"), "staffid is null");
        }
    }


        @Test
        public void JDBCgetAdminRequest1() throws SQLException{

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

                  try (ResultSet result = dbConnector.executeQuery(
                    "SELECT * FROM reg_office.staff WHERE surname = ? AND name = ?", lastName, firstName)) {

                while (result.next()) {
                    // обработка результатов
                    String surname = result.getString("surname");
                    String name = result.getString("name");
                    System.out.println(surname + " " + name);

                    Assertions.assertTrue(result.next(), "Нет данных в результате запроса");
                    assertAll("Проверка данных админа",
                            () -> assertEquals(firstName, result.getString("name")),
                            () -> assertEquals(lastName, result.getString("surname")),
                            () -> assertNotNull(result.getString("staffid")),
                            () -> assertNotNull(result.getString("passportnumber")),
                            () -> assertNotNull(result.getString("phonenumber")),
                            () -> assertNotNull(result.getString("middlename")),
                            () -> assertNotNull(result.getTimestamp("dateofbirth"))
                    );
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


}
