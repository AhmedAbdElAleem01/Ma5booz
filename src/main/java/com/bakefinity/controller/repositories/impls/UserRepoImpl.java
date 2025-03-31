package com.bakefinity.controller.repositories.impls;

import com.bakefinity.controller.repositories.interfaces.UserRepo;
import com.bakefinity.model.dtos.User;

import java.sql.*;

public class UserRepoImpl implements UserRepo {
    String url = "jdbc:mysql://localhost:3306/ecommercedb";
    String username = "root";
    String password = "root";

    @Override
    public int createUser(User user) throws SQLException {
        if (user == null) {
            System.err.println("Error creating user: User is null");
            return -1;
        }
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try(Connection connection = DriverManager.getConnection(url, username, password);) {
            String query = "INSERT INTO User (name, email, password, phoneNumber, creditLimit, birthDate, job, username) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try(PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);) {
                statement.setString(1, user.getName());
                statement.setString(2, user.getEmail());
                statement.setString(3, user.getPassword());
                statement.setString(4, user.getPhoneNumber());
                statement.setDouble(5, user.getCreditLimit());
                statement.setDate(6, ((user.getBirthDate()) != null) ? (new java.sql.Date(user.getBirthDate().getTime())) : null);
                statement.setString(7, user.getJob());
                statement.setString(8, user.getUsername());
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected <= 0) {
                    System.err.println("Failed to create user");
                    return -1;
                } else {
                    System.out.println("user is created successfully");
                    try (ResultSet resultSet = statement.getGeneratedKeys()) {
                        if (resultSet.next()) {
                            int newUserId = resultSet.getInt(1); // getGeneratedKeys() -> "id" (wrong), 1 (right)
                            System.out.println("User created successfully with ID: " + newUserId);
                            return newUserId;
                        }
                    }
                    System.err.println("Failed to retrieve user ID");
                    return -1;
                }
            }
        }
    }

    @Override
    public boolean isFoundUsername(String name) throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try(Connection connection = DriverManager.getConnection(url, username, password);) {
            String query = "SELECT id FROM User WHERE username = ?";
            try(PreparedStatement statement = connection.prepareStatement(query);) {
                statement.setString(1, name);
                try(ResultSet resultSet = statement.executeQuery();) {
                    return resultSet.next();
                }
            }
        }
    }

    @Override
    public boolean isFoundEmail(String email) throws SQLException{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try(Connection connection = DriverManager.getConnection(url, username, password);) {
            String query = "SELECT id FROM User WHERE email = ?";
            try(PreparedStatement statement = connection.prepareStatement(query);) {
                statement.setString(1, email);
                try(ResultSet resultSet = statement.executeQuery();) {
                    return resultSet.next();
                }
            }
        }
    }
}