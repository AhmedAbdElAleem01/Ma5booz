package com.bakefinity.controller.repositories.impls;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import com.bakefinity.config.DatabaseConnection;
import com.bakefinity.controller.repositories.interfaces.ProfileRepo;
import com.bakefinity.model.entities.Address;
import com.bakefinity.model.entities.User;

public class ProfileRepoImpl implements ProfileRepo{
    
    @Override
    public Optional<Address> findUserAddressById(int id) {
        String query = "SELECT * FROM address WHERE userId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) { 
                Address address = new Address();
                address.setCity(rs.getString("city"));
                address.setCountry(rs.getString("country"));
                address.setStreet(rs.getString("street"));
                address.setBuildingNo(rs.getString("buildingNo"));

                return Optional.of(address); 
            }
        } catch (SQLException e) {
            System.out.println("DB ERROR: Failed to find user's address: " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> updateCreditLimit(User user, String creditLimit) {
        String query = "UPDATE users SET creditLimit = ? WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
    
            pstmt.setString(1, creditLimit);
            pstmt.setInt(2, user.getId());
            
            int rowsUpdated = pstmt.executeUpdate();
            
            if (rowsUpdated > 0) {
                user.setCreditLimit(new BigDecimal(creditLimit)); //update
                System.out.println("new credit limit " + user.getCreditLimit());
                return Optional.of(user);
            }else{
                System.out.println("DB ERROR: Failed to update user's credit limit");
                return Optional.empty();
            }
        } catch (SQLException e) {
            System.out.println("DB ERROR: Failed to update user's credit limit: " + e.getMessage());
        }
        return Optional.empty(); 
    }

    @Override
    public Optional<Address> updateShippingAddress(User user, String country, String city, String street, String BNo) {
        String query = "UPDATE address SET country = ? , city = ? , street = ? , buildingNo = ? WHERE userId = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
    
            pstmt.setString(1, country);
            pstmt.setString(2, city);
            pstmt.setString(3, street);
            pstmt.setString(4, BNo);
            pstmt.setInt(5, user.getId());
            
            int rowsUpdated = pstmt.executeUpdate();
            
            if (rowsUpdated > 0) {
                Address address = new Address();
                address.setCountry(country);
                address.setCity(city);
                address.setStreet(street);
                address.setBuildingNo(BNo);
                return Optional.of(address);
            }else{
                System.out.println("DB ERROR: Failed to update user's shipping address");
                return Optional.empty();
            }
        } catch (SQLException e) {
            System.out.println("DB ERROR: Failed to update user's shipping address: " + e.getMessage());
        }
        return Optional.empty(); 
    }

    @Override
    public Optional<User> updateAccountDetails(User user, String username, String job, String email) {
        String query = "UPDATE users SET username = ? , email = ? , job = ? WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
    
            pstmt.setString(1, username);
            pstmt.setString(2, email);
            pstmt.setString(3, job);
            pstmt.setInt(4, user.getId());
            
            int rowsUpdated = pstmt.executeUpdate();
            
            if (rowsUpdated > 0) {
                user.setUsername(username); 
                user.setEmail(email);
                user.setJob(job);
                return Optional.of(user);
            }else{
                System.out.println("DB ERROR: Failed to update user's account details");
                return Optional.empty();
            }
        } catch (SQLException e) {
            System.out.println("DB ERROR: Failed to update user's account details: " + e.getMessage());
        }
        return Optional.empty(); 
    }

    @Override
    public Optional<User> updatePassword(User user, String newPass) {
        String query = "UPDATE users SET password = ?  WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
    
            pstmt.setString(1, newPass);
            pstmt.setInt(2, user.getId());
            
            int rowsUpdated = pstmt.executeUpdate();
            
            if (rowsUpdated > 0) {
                user.setPassword(newPass);
                return Optional.of(user);
            }else{
                System.out.println("DB ERROR: Failed to update user's password");
                return Optional.empty();
            }
        } catch (SQLException e) {
            System.out.println("DB ERROR: Failed to update user's account details: " + e.getMessage());
        }
        return Optional.empty(); 
    }

    public boolean isUsernameTaken(String username) {
        String query = "SELECT COUNT(*) FROM users WHERE username = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; 
            }
        } catch (SQLException e) {
            System.out.println("DB ERROR: Failed to validate new username " + e.getMessage());
        }
        return false;
    }

    @Override
    public Optional<User> updatePhoneNumber(User user, String mobile) {
        String query = "UPDATE users SET phoneNumber = ?  WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
    
            pstmt.setString(1, mobile);
            pstmt.setInt(2, user.getId());
            
            int rowsUpdated = pstmt.executeUpdate();
            
            if (rowsUpdated > 0) {
                user.setPhoneNumber(mobile);;
                return Optional.of(user);
            }else{
                System.out.println("DB ERROR: Failed to update user's phone number");
                return Optional.empty();
            }
        } catch (SQLException e) {
            System.out.println("DB ERROR: Failed to update user's phone number: " + e.getMessage());
        }
        return Optional.empty(); 
    }   
    
}
