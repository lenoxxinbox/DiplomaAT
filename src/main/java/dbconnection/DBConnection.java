package dbconnection;

import model.Car;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.sql.DriverManager.getConnection;

public class DBConnection {

    Connection connection = null;

    public void connect(String url, String user, String password) throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = getConnection(url, user, password);
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
        int money = 0;
        boolean isMale = false;

        while (result.next()) {
            firstName = result.getString("first_name");
            secondName = result.getString("second_name");
            age = result.getInt("age");
            money = result.getInt("money");
            isMale = result.getBoolean("sex");
        }
        return new User(firstName, secondName, age, money, isMale);
    }

    public Car getCarById(String id) throws SQLException {
        String query = "SELECT * FROM car WHERE id = " + id;
        ResultSet result = executeQuery(query);
        String  engine_type_id = null;
        String mark = null;
        String model = null;
        Double price = null;

        while (result.next()) {
            engine_type_id = result.getString("engine_type_id");
            if ("2".equals(engine_type_id)){
                engine_type_id = "Diesel";
            }
            mark = result.getString("mark");
            model = result.getString("model");
            price = result.getDouble("price");
        }
        return new Car(engine_type_id, mark, model, price);
    }
}
