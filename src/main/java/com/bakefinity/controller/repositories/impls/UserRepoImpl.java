package com.bakefinity.controller.repositories.impls;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

import com.bakefinity.controller.repositories.interfaces.UserRepo;
import com.bakefinity.model.dtos.*;

public class UserRepoImpl implements UserRepo{
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

   public Optional<User> findByEmailAndPassword(String email, String pass) { 
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String query = "SELECT * FROM user WHERE email = ? AND password = ?";
        try (Connection conn = DriverManager.getConnection(url, username, password);
            PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, email);
            pstmt.setString(2, pass);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) { 
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setBirthDate(rs.getDate("birthDate"));
                user.setCreditLimit(rs.getDouble("creditLimit"));
                user.setPhoneNumber(rs.getString("phoneNumber"));
                user.setJob(rs.getString("job"));

                return Optional.of(user); 
            }
        } catch (SQLException e) {
            System.out.println("Failed to find user: " + e.getMessage());
        }
        return Optional.empty();
    }    
    public Optional<User> findById(int id) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String query = "SELECT * FROM user WHERE id = ?";
        
        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement stmt = conn.prepareStatement(query)) {
    
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
    
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setBirthDate(rs.getDate("birthDate"));
                user.setCreditLimit(rs.getDouble("creditLimit"));
                user.setPhoneNumber(rs.getString("phoneNumber"));
                user.setJob(rs.getString("job"));
                return Optional.of(user);
            }
        } catch (SQLException e) {
            System.out.println("Failed to find user: " + e.getMessage());
        }
        return Optional.empty(); 
    }
    
}
