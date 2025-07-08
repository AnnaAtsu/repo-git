package org.example.jdbc;

import org.example.ConfProperties;
import org.junit.platform.commons.logging.LoggerFactory;

import java.sql.*;
import java.util.logging.Logger;

public class ConnectionJDBC {
    private static final String DBurl = ConfProperties.getProperty("DBurl");
    private static final String user = ConfProperties.getProperty("user");
    private static final String password = ConfProperties.getProperty("user_senla");
    private static final Logger log = (Logger) LoggerFactory.getLogger(ConnectionJDBC.class);
    private static Connection con = null;
    private static Statement stmt = null;
    private static PreparedStatement pstmt = null;
    private static ResultSet rs = null;


    public static Connection connectTo() {
        log.info("Connect to DB " + DBurl + " by " + user);
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(DBurl, user, password);
            log.info("Connection is successfull");
        } catch (SQLException e) {
            log.info("Connection failed! " + e.getMessage());
        } catch (ClassNotFoundException e) {
            log.info(e.getMessage());
        }
        return con;
    }
    public static String checkApplicationById(Integer applicationId) throws SQLException {
        String selectQuery = "SELECT * FROM applications WHERE applicationid = " + applicationId;
        String value;
        stmt = connectTo().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        log.info("Send request to DB: " + selectQuery);
        rs = stmt.executeQuery(selectQuery);
        String value1 = null;
        while (rs.next()) {
            value1 = rs.getString(1);
            System.out.println(value1);
        }
        return value1;
    }


    public static String checkAdminRequestById(Integer staffId) throws SQLException {
        String selectQuery = "SELECT * FROM staff WHERE staffid = ?";
        String value;
        pstmt = connectTo().prepareStatement(selectQuery,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        pstmt.setInt(1, staffId);
        log.info("Send request to DB: " + pstmt.toString());
        if(rs.next()) {
            value = rs.getString("staffId");
        }
        else {
            value = null;
        }
        return value;
    }

}
