package com.bakefinity.controller.repositories.impls;

import java.util.Optional;

import com.bakefinity.controller.repositories.interfaces.AdminRepo;
import com.bakefinity.model.dtos.UserDTO;
import com.bakefinity.model.entities.Admin;
import com.bakefinity.utils.EntityManagerFactorySingleton;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

public class AdminRepoImpl implements AdminRepo{
    private EntityManagerFactory emf = EntityManagerFactorySingleton.getInstance();

   public Optional<UserDTO> findByEmailAndPassword(String email, String password) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Admin> query = em.createQuery(
                "SELECT u FROM Admin u WHERE u.email = :email AND u.password = :password", Admin.class
            );
            query.setParameter("email", email);
            query.setParameter("password", password);
            UserDTO admin = new UserDTO();
            admin.setId(query.getSingleResult().getId());
            admin.setName(query.getSingleResult().getName());
            admin.setEmail(query.getSingleResult().getEmail());
            admin.setPassword(query.getSingleResult().getPassword());
            return Optional.of(admin);
        } catch (NoResultException e) {
            return Optional.empty();
        } finally {
            em.close(); 
        }
    }    

    
}