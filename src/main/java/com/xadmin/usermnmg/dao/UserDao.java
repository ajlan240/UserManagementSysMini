package com.xadmin.usermnmg.dao;

import com.mysql.cj.protocol.Resultset;
import com.xadmin.usermnmg.bean.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private String url = "jdbc:mysql://localhost:3306/user_database";
    private String username = "root";
    private String password = "920756";
    private String driver = "com.mysql.cj.jdbc.Driver";

    private static final String INSERT_USER_SQL =
            "INSERT INTO users (name, email, country) VALUES (?, ?, ?);";
    private static final String SELECT_BY_ID = "SELECT id, name, email, country FROM users WHERE id=?";

    private static final String SELECT_ALL_USERS = "SELECT * FROM users";

    private static final String DELETE_USERS_SQL = "DELETE FROM users WHERE id=?";
    private static final String UPDATE_USERS_SQL = "UPDATE users SET name=?, email=?, country=? WHERE id=? ";

    public UserDao(){}

    protected Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    // insert user method
    public void insertUser(User user) throws SQLException{
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getCountry());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // select user by id

    public User selectUser(int id) {
        User user = null;
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                user = new User(id, name, email, country);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
    //select all users
    public List<User> selectAllUser() {
        User user = null;
        List<User> userList = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                user = new User(id, name, email, country);
                userList.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }

    // update user method

    public boolean updateUSer(User user) {
        boolean rowUpdated;
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USERS_SQL);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getCountry());
            preparedStatement.setInt(4, user.getId());
            rowUpdated = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return rowUpdated;
    }

    // delete a user
    public boolean deleteUser(int id){
        boolean rowDeleted;
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USERS_SQL);
            preparedStatement.setInt(1, id);
            rowDeleted = preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rowDeleted;
    }
}
