package com.bakefinity.controller.repositories.impls;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.bakefinity.controller.repositories.interfaces.UserRepo;
import com.bakefinity.model.dtos.*;
import com.bakefinity.model.entities.User;
import com.bakefinity.utils.ConnectionManager;

public class UserRepoImpl implements UserRepo{
    @Override
    public int createUser(UserDTO user) throws SQLException {
        if (user == null) {
            System.err.println("Error creating user: User is null");
            return -1;
        }
        try(Connection connection = ConnectionManager.getConnection();) {
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
        try(Connection connection = ConnectionManager.getConnection();) {
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
        try(Connection connection = ConnectionManager.getConnection();) {
            String query = "SELECT id FROM User WHERE email = ?";
            try(PreparedStatement statement = connection.prepareStatement(query);) {
                statement.setString(1, email);
                try(ResultSet resultSet = statement.executeQuery();) {
                    return resultSet.next();
                }
            }
        }
    }

   public Optional<UserDTO> findByEmailAndPassword(String email, String pass) { 
        String query = "SELECT * FROM user WHERE email = ? AND password = ?";
        try (Connection conn = ConnectionManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, email);
            pstmt.setString(2, pass);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) { 
                UserDTO user = new UserDTO();
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
    public Optional<UserDTO> findById(int id) {
        String query = "SELECT * FROM user WHERE id = ?";
        
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
    
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
    
            if (rs.next()) {
                UserDTO user = new UserDTO();
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
    public List<User> getAllUsers() throws SQLException {
        List<User> customers = new ArrayList<>();
        
        String query = "SELECT * FROM user";
        try (Connection conn = ConnectionManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                LocalDate bd = rs.getDate("birthDate") != null ? rs.getDate("birthDate").toLocalDate() : null;
                User customer = new User(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("phoneNumber"),
                    rs.getDouble("creditLimit"),
                    bd,
                    rs.getString("job"),
                    rs.getTimestamp("createdAt").toLocalDateTime()
                );
                customers.add(customer);
            }
        }
        return customers;
    }
    
}
