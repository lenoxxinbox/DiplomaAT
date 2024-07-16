package utils;

import java.sql.*;

public class DBConnection {
    private final static String DB_USERNAME = ConfigReader.get("dbUser");
    private final static String DB_PASSWORD = ConfigReader.get("dbPassword");
    private final static String DB_URL = ConfigReader.get("dbUrl");

    Connection connection = null;

    public void connect() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        }
    }

    public void disconnect() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    public ResultSet executeQuery(String query) throws SQLException {
        connect();
        PreparedStatement statement = connection.prepareStatement(query);
        return statement.executeQuery();
    }

    public ResultSet getUserById(String id) throws SQLException {
        String query = "SELECT * FROM person WHERE id = " + id;
        return executeQuery(query);
    }
}
