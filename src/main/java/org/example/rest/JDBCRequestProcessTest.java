package org.example.rest;

import com.github.javafaker.Faker;
import org.example.client.SpecConfig;
import org.example.dto.ProcessRequest;
import org.example.jdbc.JDBCpostgresql;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.*;

public class JDBCRequestProcessTest {
    ProcessRequest request1;
    private JDBCpostgresql dbConnector;
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
        request1 = new ProcessRequest(
                Integer.valueOf(faker.options().option("51609", "51607","51602", "51600")),
                null,  // Явное указание null
                faker.options().option("approve", "reject")
        );
    }

    @Test
    public void getJDBCRequestProcess() throws SQLException {
        String applicantId = "51609";
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
        dbConnector.connect();
        try (ResultSet result = dbConnector.executeQuery("SELECT * FROM reg_office.applications WHERE applicationid = " + applicantId))
        {

            Assertions.assertTrue(result.next(), "Application not found in database");
            assertEquals(applicantId, result.getString("applicationid"));
            assertEquals("under consideration", result.getString("statusofapplication"), "statusofapplication is null");
            assertNotNull(result.getTimestamp("dateofapplication"), "Creation date is null");
            assertNull(result.getString("staffid"), "staffid name is missing");


        }
        dbConnector.disconnect();

    }

}
