package com.bakefinity.controller.repositories.impls;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import com.bakefinity.config.DatabaseConnection;
import com.bakefinity.controller.repositories.interfaces.UserRepo;
import com.bakefinity.model.entities.User;

public class UserRepoImpl implements UserRepo{

   public Optional<User> findByEmailAndPassword(String email, String password) {
        String query = "SELECT * FROM users WHERE email = ? AND password = ?";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, email);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) { 
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setBirthDate(rs.getDate("birthDate"));
                user.setCreditLimit(rs.getBigDecimal("creditLimit"));
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
        String query = "SELECT * FROM users WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
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
                user.setCreditLimit(rs.getBigDecimal("creditLimit"));
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

