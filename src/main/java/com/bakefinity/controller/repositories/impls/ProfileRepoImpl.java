package com.bakefinity.controller.repositories.impls;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import com.bakefinity.controller.repositories.interfaces.ProfileRepo;
import com.bakefinity.model.dtos.*;
import com.bakefinity.utils.ConnectionManager;

public class ProfileRepoImpl implements ProfileRepo{    
    @Override
    public Optional<AddressDTO> findUserAddressById(int id) {
        String query = "SELECT * FROM address WHERE userId = ?";
        try (Connection conn = ConnectionManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) { 
                AddressDTO address = new AddressDTO();
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
    public Optional<UserDTO> updateCreditLimit(UserDTO user, double creditLimit) {
        String query = "UPDATE user SET creditLimit = ? WHERE id = ?";
        
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
    
            pstmt.setDouble(1, creditLimit);
            pstmt.setInt(2, user.getId());
            
            int rowsUpdated = pstmt.executeUpdate();
            
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
    public Optional<AddressDTO> updateShippingAddress(UserDTO user, String country, String city, String street, String BNo) {
        String query = "UPDATE address SET country = ? , city = ? , street = ? , buildingNo = ? WHERE userId = ?";
        
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
    
            pstmt.setString(1, country);
            pstmt.setString(2, city);
            pstmt.setString(3, street);
            pstmt.setString(4, BNo);
            pstmt.setInt(5, user.getId());
            
            int rowsUpdated = pstmt.executeUpdate();
            
            if (rowsUpdated > 0) {
                AddressDTO address = new AddressDTO();
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
    public Optional<UserDTO> updateAccountDetails(UserDTO user, String name, String job, String email) {
        String query = "UPDATE user SET username = ? , email = ? , job = ? WHERE id = ?";
        
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
    
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, job);
            pstmt.setInt(4, user.getId());
            
            int rowsUpdated = pstmt.executeUpdate();
            
            if (rowsUpdated > 0) {
                user.setUsername(name); 
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
    public Optional<UserDTO> updatePassword(UserDTO user, String newPass) {
        String query = "UPDATE user SET password = ?  WHERE id = ?";
        
        try (Connection conn = ConnectionManager.getConnection();
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

    public boolean isUsernameTaken(String name) {
        String query = "SELECT COUNT(*) FROM user WHERE username = ?";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, name);
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
    public Optional<UserDTO> updatePhoneNumber(UserDTO user, String mobile) {
        String query = "UPDATE user SET phoneNumber = ?  WHERE id = ?";
        
        try (Connection conn = ConnectionManager.getConnection();
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
