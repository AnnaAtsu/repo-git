package org.example.jdbc;

import org.example.ConfProperties;

import java.sql.*;

public class JDBCpostgresql {
    private static final String DBurl = ConfProperties.getProperty("DBurl");
    private static final String user = ConfProperties.getProperty("user");
    private static final String password = ConfProperties.getProperty("user_senla");
    private Connection connection;
    /**
     * Устанавливает соединение с базой данных PostgreSQL
     */
    public void connect() throws SQLException {
        try {
            // Регистрируем драйвер
            Class.forName("org.postgresql.Driver");

            // Устанавливаем соединение
            connection = DriverManager.getConnection(DBurl, user, password);

            System.out.println("Successfully connected to PostgreSQL database");
        } catch (ClassNotFoundException e) {
            throw new SQLException("PostgreSQL JDBC Driver not found", e);
        }
    }

    /**
     * Закрывает соединение с базой данных
     */
    public void disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection closed");
            }
        } catch (SQLException e) {
            System.err.println("Error while closing connection: " + e.getMessage());
        }
    }

    /**
     * Выполняет SQL запрос и возвращает ResultSet
     */
    public ResultSet executeQuery(String sql) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery(sql);
    }

    /**
     * Выполняет SQL запрос с параметрами (PreparedStatement)
     */
    public ResultSet executeQuery(String sql, Object... params) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            statement.setObject(i + 1, params[i]);
        }
        return statement.executeQuery();
    }

    /**
     * Выполняет SQL команду (UPDATE, INSERT, DELETE)
     */
    public int executeUpdate(String sql) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            return statement.executeUpdate(sql);
        }
    }

    /**
     * Проверяет соединение с базой данных
     */
    public boolean isConnected() throws SQLException {
        return connection != null && !connection.isClosed();
    }

    /**
     * Получает текущее соединение
     */
    public Connection getConnection() {
        return connection;
    }

}
