package com.bakefinity.controller.repositories.impls;

import com.bakefinity.controller.repositories.interfaces.AddressRepo;
import com.bakefinity.model.dtos.AddressDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import com.bakefinity.utils.ConnectionManager;

public class AddressRepoImpl implements AddressRepo {
    @Override
    public boolean createAddress(AddressDTO address) throws SQLException {
        if (address == null) {
            System.err.println("Error creating address: Address is null");
            return false;
        }
        try(Connection connection = ConnectionManager.getConnection();) {
            String query = "INSERT INTO Address (userId, buildingNo, street, city, country) VALUES (?, ?, ?, ?, ?)";
            try(PreparedStatement statement = connection.prepareStatement(query);) {
                statement.setInt(1, address.getUserId());
                statement.setInt(2, address.getBuildingNo());
                statement.setString(3, address.getStreet());
                statement.setString(4, address.getCity());
                statement.setString(5, address.getCountry());
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected <= 0) {
                    System.err.println("Failed to create address");
                    return false;
                } else {
                    System.out.println("address is created successfully");
                    return true;
                }
            }
        }
    }
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
}
