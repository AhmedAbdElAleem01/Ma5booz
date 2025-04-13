package com.bakefinity.controller.repositories.impls;

import com.bakefinity.controller.repositories.interfaces.AddressRepo;
import com.bakefinity.model.dtos.AddressDTO;
import com.bakefinity.model.entities.Address;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import com.bakefinity.utils.ConnectionManager;
import com.bakefinity.utils.EntityManagerFactorySingleton;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

public class AddressRepoImpl implements AddressRepo {
    private EntityManagerFactory emf = EntityManagerFactorySingleton.getInstance();

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
        EntityManager em = emf.createEntityManager();
    
        try {
            TypedQuery<Address> query = em.createQuery(
                "SELECT a FROM Address a WHERE a.user.id = :userId", Address.class
            );
            query.setParameter("userId", id);

            Address addressEntity = query.getSingleResult();
            AddressDTO addressDTO = new AddressDTO();
            addressDTO.setCity(addressEntity.getCity());
            addressDTO.setCountry(addressEntity.getCountry());
            addressDTO.setStreet(addressEntity.getStreet());
            addressDTO.setBuildingNo(Integer.parseInt(addressEntity.getBuildingNo()));
            return Optional.of(addressDTO);
        } catch (NoResultException e) {
            return Optional.empty();
        } catch (Exception e) {
            System.out.println("DB ERROR: Failed to find user's address: " + e.getMessage());
            return Optional.empty();
        } finally {
            em.close();
        }
    }
}
