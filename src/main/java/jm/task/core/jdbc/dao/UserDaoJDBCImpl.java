package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {


    public UserDaoJDBCImpl() throws SQLException, ClassNotFoundException {

    }

    public void createUsersTable() {
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement();) {
            String sql2 = """
                    CREATE TABLE IF NOT EXISTS users1 
                    (id INT PRIMARY KEY AUTO_INCREMENT ,
                    name VARCHAR(20),
                    lastName VARCHAR(20),
                    age INT)""";
            statement.executeUpdate(sql2);
var name = "fwawcac";

        } catch (SQLException e) {
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement();) {
            String sql3 = "DROP TABLE IF EXISTS users1";
            statement.executeUpdate(sql3);
        } catch (SQLException e) {
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.getConnection();) {
            String sql = "INSERT INTO users1 (name, lastName, age) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {

        try (Connection connection = Util.getConnection();) {
            String sql1 = ("DELETE FROM users1 WHERE id = ?");
            PreparedStatement preparedStatement = connection.prepareStatement(sql1);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate(sql1);
        } catch (SQLException e) {
        }
    }
    public List<User> getAllUsers() {
        List<User> animals = new ArrayList<>();
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement();) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users1");
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastName");
                byte age = resultSet.getByte("age");
                animals.add(new User(id, name, lastName, age));
            }
        } catch (SQLException e) {}
        return animals;
    }
    public void cleanUsersTable() {
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement();) {
            String sql4 = "TRUNCATE TABLE users1";
            statement.executeUpdate(sql4);
        } catch (SQLException e) {}
    }


}





