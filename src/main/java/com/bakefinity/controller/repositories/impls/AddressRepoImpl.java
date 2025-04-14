package com.bakefinity.controller.repositories.impls;

import com.bakefinity.controller.repositories.interfaces.AddressRepo;
import com.bakefinity.model.dtos.AddressDTO;

import java.sql.SQLException;
import java.util.Optional;

import com.bakefinity.model.entities.Address;
import com.bakefinity.model.entities.User;
import com.bakefinity.utils.EntityManagerFactorySingleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

public class AddressRepoImpl implements AddressRepo {
    @Override
    public boolean createAddress(AddressDTO addressDTO) throws SQLException {
        if (addressDTO == null) {
            System.err.println("Error creating address: Address is null");
            return false;
        }
        EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
        try {
            em.getTransaction().begin();
            User user = em.find(User.class, addressDTO.getUserId());
            if(user == null){
                System.out.println("there is no user with id = " + addressDTO.getUserId());
                return false;
            }
            Address address = new Address(user, addressDTO.getBuildingNo(), addressDTO.getStreet(), addressDTO.getCity(), addressDTO.getCountry());
            em.persist(address);
            em.getTransaction().commit();
            return true;
        }
        finally{
            em.close();
        }
    }

    @Override
    public Optional<AddressDTO> findUserAddressById(int id) {
        EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
    
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
            addressDTO.setBuildingNo(addressEntity.getBuildingNo());
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