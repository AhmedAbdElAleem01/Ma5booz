package com.bakefinity.controller.repositories.impls;

import com.bakefinity.controller.repositories.interfaces.AddressRepo;
import com.bakefinity.model.dtos.AddressDTO;

import java.awt.geom.QuadCurve2D;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.bakefinity.model.entities.Address;
import com.bakefinity.model.entities.User;
import com.bakefinity.utils.ConnectionManager;
import com.bakefinity.utils.EntityManagerFactorySingleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

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
            em.getTransaction().begin();
            User user = em.find(User.class, id);
            if(user == null){
                System.out.println("there is no user with id = " + id);
                return Optional.empty();
            }
            Query query = em.createQuery("from Address a where a.user=:user").setParameter("user", user);
            List<Address> addresses = query.getResultList();
            if (!addresses.isEmpty()) {
                AddressDTO addressDTO = new AddressDTO(addresses.get(0).getUser().getId(), addresses.get(0).getBuildingNo(), addresses.get(0).getStreet(), addresses.get(0).getCity(), addresses.get(0).getCountry());
                return Optional.of(addressDTO);
            }
            em.getTransaction().commit();
            return Optional.empty();
        }
        finally {
            em.close();
        }
    }
}