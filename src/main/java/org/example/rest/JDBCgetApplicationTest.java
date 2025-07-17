package org.example.rest;

import org.example.client.SpecConfig;
import org.example.dto.GetApplicationResponse;
import org.example.jdbc.JDBCpostgresql;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.ResultSet;
import java.sql.SQLException;
import static io.restassured.RestAssured.given;

import static org.junit.jupiter.api.Assertions.*;

public class JDBCgetApplicationTest {
    private JDBCpostgresql dbConnector;
    @BeforeEach
    public void setUpDB() throws SQLException {
        // Инициализация подключения к БД
        dbConnector = new JDBCpostgresql();
        dbConnector.connect();
    }


    @Test
    public void getApplicationidJDBC() throws SQLException {
        String applicationId = "51590";
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
        dbConnector.connect();
        try (ResultSet result = dbConnector.executeQuery("SELECT * FROM reg_office.applications WHERE applicationid = " + applicationId))
               {
            Assertions.assertTrue(result.next(), "Application not found in database");
            assertEquals(applicationId, result.getString("applicationid"));
        }
        dbConnector.disconnect();
    }

    @Test
    public void checkApplicationById() throws SQLException{
        String applicationId = "51590";
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
        dbConnector.connect();
        try (ResultSet result = dbConnector.executeQuery("SELECT * FROM reg_office.applications WHERE applicationid = " + applicationId))
        {

            Assertions.assertTrue(result.next(), "Application not found in database");
            assertEquals(applicationId, result.getString("applicationid"));
            assertEquals("53116", result.getString("citizenid"), "Status is null");
            assertNotNull(result.getTimestamp("dateofapplication"), "Creation date is null");
            assertNull(result.getString("from_draft"), "from_draft name is missing");


        }
        dbConnector.disconnect();
    }


    @AfterEach
    public void tearDown() {
        // Закрываем соединение с БД
        if (dbConnector != null) {
            dbConnector.disconnect();
        }
    }
}
