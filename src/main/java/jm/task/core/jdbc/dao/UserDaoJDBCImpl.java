package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static final Connection connection = Util.getConnection();
    private static PreparedStatement preparedStatement;

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() throws SQLException {
        preparedStatement = connection.prepareStatement("""
                CREATE TABLE IF NOT EXISTS Users(
                    id bigint,
                    name varchar,
                    last_name varchar,
                    age smallint
                );""");
        preparedStatement.executeUpdate();
    }

    public void dropUsersTable() throws SQLException {
        preparedStatement = connection.prepareStatement("DROP TABLE IF EXISTS Users");
        preparedStatement.executeUpdate();
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        preparedStatement = connection.prepareStatement("INSERT INTO Users VALUES(9, ?, ?, ?);");
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, lastName);
        preparedStatement.setByte(3, age);
        preparedStatement.executeUpdate();
    }

    public void removeUserById(long id) throws SQLException {
        preparedStatement = connection.prepareStatement("DELETE FROM Users WHERE id=?");
        preparedStatement.setLong(1, id);
        preparedStatement.executeUpdate();
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM Users;";
        preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getLong("id"));
            user.setName(resultSet.getString("name"));
            user.setLastName(resultSet.getString("last_name"));
            user.setAge(resultSet.getByte("age"));
            users.add(user);
        }
        return users;
    }

    public void cleanUsersTable() throws SQLException {
        createUsersTable(); // для избежания ошибки, если таблицы не существует
        preparedStatement = connection.prepareStatement("DELETE FROM Users");
        preparedStatement.executeUpdate();
    }
}
