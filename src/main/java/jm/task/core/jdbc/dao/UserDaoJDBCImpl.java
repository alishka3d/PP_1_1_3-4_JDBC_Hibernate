package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static Connection connection;
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;

    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "999138";

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() throws SQLException {
        preparedStatement = connection.prepareStatement("create table Users(\n" +
                "    id bigint,\n" +
                "    name varchar,\n" +
                "    last_name varchar,\n" +
                "    age smallint\n" +
                ");");
        preparedStatement.executeUpdate();
    }

    public void dropUsersTable() throws SQLException {
        preparedStatement = connection.prepareStatement("DROP TABLE Users;");
        preparedStatement.executeUpdate();
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        preparedStatement = connection.prepareStatement("INSERT INTO Users VALUES(9, ?, ?, ?);");
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, lastName);
        preparedStatement.setByte(3, age);
        preparedStatement.executeUpdate();
    }

    public void removeUserById(long id) {

    }

    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<User>();
        String query = "SELECT * FROM Users;";
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
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

    public void cleanUsersTable() {

    }
}
