package db;

import model.User;

import java.sql.*;

public class DBConnection {

    Connection connection = null;

    public void connect(String url, String user, String password) throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(url, user, password);
        }
    }

    public void disconnect() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    public ResultSet executeQuery(String query) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);
        return statement.executeQuery();
    }

    public User getUserById(String id) throws SQLException {
        String query = "SELECT * FROM person WHERE id = " + id;
        ResultSet result = executeQuery(query);
        String firstName = null;
        String secondName = null;
        int age = 0;
        double money = 0.0;
        boolean isMale = false;

        while (result.next()) {
            firstName = result.getString("first_name");
            secondName = result.getString("second_name");
            age = result.getInt("age");
            money = result.getDouble("money");
            isMale = result.getBoolean("sex");
        }
        return new User(firstName, secondName, age, money, isMale);
    }
}
