package com.bakefinity.controller.repositories.impls;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import com.bakefinity.controller.repositories.interfaces.AdminRepo;
import com.bakefinity.model.dtos.UserDTO;
import com.bakefinity.utils.ConnectionManager;

public class AdminRepoImpl implements AdminRepo{

   public Optional<UserDTO> findByEmailAndPassword(String email, String password) {
        String query = "SELECT * FROM admin WHERE email = ? AND password = ?";
        try (Connection conn = ConnectionManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, email);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) { 
                UserDTO user = new UserDTO();
                user.setUsername(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                
                return Optional.of(user); 
            }
        } catch (SQLException e) {
            System.out.println("Failed to find user: " + e.getMessage());
        }
        return Optional.empty();
    }

    
}