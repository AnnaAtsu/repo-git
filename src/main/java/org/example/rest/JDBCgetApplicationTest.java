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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class JDBCgetApplicationTest {
    private JDBCpostgresql dbConnector;
    @BeforeEach
    public void setUpDB() throws SQLException {
        // Инициализация подключения к БД
        dbConnector = new JDBCpostgresql();
        dbConnector.connect();
    }


    @Test
    public void getApplicationid() throws SQLException {
        int applicationId = 51590;
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
        try (ResultSet result = dbConnector.executeQuery("SELECT * FROM applications WHERE applicationid=" + applicationId))
        //"SELECT * FROM users WHERE email = ?", пример
        //            "test@example.com"
        {
            Assertions.assertTrue(result.next(), "Application not found in database");
            assertEquals(applicationId, result.getString("applicationid"));
        }
    }
    @AfterEach
    public void tearDown() {
        // Закрываем соединение с БД
        if (dbConnector != null) {
            dbConnector.disconnect();
        }
    }
}
