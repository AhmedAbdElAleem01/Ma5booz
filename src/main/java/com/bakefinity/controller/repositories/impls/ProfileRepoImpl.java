package com.bakefinity.controller.repositories.impls;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import com.bakefinity.controller.repositories.interfaces.ProfileRepo;
import com.bakefinity.model.dtos.*;

public class ProfileRepoImpl implements ProfileRepo{
    String url = "jdbc:mysql://localhost:3306/ecommercedb";
    String username = "root";
    String password = "root";
    
    @Override
    public Optional<Address> findUserAddressById(int id) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String query = "SELECT * FROM address WHERE userId = ?";
        try (Connection conn = DriverManager.getConnection(url, username, password);
            PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) { 
                Address address = new Address();
                address.setCity(rs.getString("city"));
                address.setCountry(rs.getString("country"));
                address.setStreet(rs.getString("street"));
                address.setBuildingNo(rs.getInt("buildingNo"));

                return Optional.of(address); 
            }
        } catch (SQLException e) {
            System.out.println("DB ERROR: Failed to find user's address: " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> updateCreditLimit(User user, double creditLimit) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String query = "UPDATE user SET creditLimit = ? WHERE id = ?";
        
        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
    
            pstmt.setDouble(1, creditLimit);
            pstmt.setInt(2, user.getId());
            
            int rowsUpdated = pstmt.executeUpdate();
            System.out.println("newCreditLimit= " + creditLimit + " userId= " + user.getId());
            
            if (rowsUpdated > 0) {
                user.setCreditLimit(creditLimit); //update
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
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String query = "UPDATE address SET country = ? , city = ? , street = ? , buildingNo = ? WHERE userId = ?";
        
        try (Connection conn = DriverManager.getConnection(url, username, password);
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
                address.setBuildingNo(Integer.parseInt(BNo));
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
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String query = "UPDATE user SET username = ? , email = ? , job = ? WHERE id = ?";
        
        try (Connection conn = DriverManager.getConnection(url, username, password);
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
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String query = "UPDATE user SET password = ?  WHERE id = ?";
        
        try (Connection conn = DriverManager.getConnection(url, username, password);
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
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String query = "SELECT COUNT(*) FROM user WHERE username = ?";
        try (Connection conn = DriverManager.getConnection(url, username, password);
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
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String query = "UPDATE user SET phoneNumber = ?  WHERE id = ?";
        
        try (Connection conn = DriverManager.getConnection(url, username, password);
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
