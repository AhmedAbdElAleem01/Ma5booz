package com.bakefinity.controller.repositories.impls;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.bakefinity.controller.repositories.interfaces.UserRepo;
import com.bakefinity.model.dtos.*;
import com.bakefinity.model.entities.User;
import com.bakefinity.utils.EntityManagerFactorySingleton;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

public class UserRepoImpl implements UserRepo{
    private EntityManagerFactory emf = EntityManagerFactorySingleton.getInstance();

    @Override
    public int createUser(UserDTO user) throws SQLException {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        if (user == null) {
            System.err.println("Error creating user: User is null");
            return -1;
        }
        try{
            tx.begin();
            User myUser = new User(
                user.getUsername(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getPhoneNumber(),
                user.getCreditLimit());
                em.persist(myUser);
            tx.commit();
            return myUser.getId();
        }catch(Exception e){
            System.err.println("Failed to create new user");
            return -1;
        }finally{
            em.close();
        }
    }
    @Override
    public boolean isFoundUsername(String name) throws SQLException {
        EntityManager em = emf.createEntityManager();
        try {
            String query = "SELECT COUNT(u) FROM User u WHERE u.username = :username";
            Long count = em.createQuery(query, Long.class)
                            .setParameter("username", name)
                            .getSingleResult();
            return count > 0;
        } finally {
            em.close();
        }
    }

    @Override
    public boolean isFoundEmail(String email) throws SQLException{
        EntityManager em = emf.createEntityManager();
        try {
            String query = "SELECT COUNT(u) FROM User u WHERE u.email = :email";
            Long count = em.createQuery(query, Long.class)
                            .setParameter("email", email)
                            .getSingleResult();
            return count > 0;
        } finally {
            em.close();
        }
    }

   public Optional<UserDTO> findByEmailAndPassword(String email, String pass) { 
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<User> query = em.createQuery(
                "SELECT u FROM User u WHERE u.email = :email AND u.password = :password", User.class
            );
            query.setParameter("email", email);
            query.setParameter("password", pass);
            UserDTO user = new UserDTO();
            user.setId(query.getSingleResult().getId());
            user.setName(query.getSingleResult().getName());
            user.setEmail(query.getSingleResult().getEmail());
            user.setPassword(query.getSingleResult().getPassword());
            user.setPhoneNumber(query.getSingleResult().getPhoneNumber());
            user.setCreditLimit(query.getSingleResult().getCreditLimit());
            user.setBirthDate(query.getSingleResult().getBirthDate());
            user.setJob(query.getSingleResult().getJob());
            user.setCreatedAt(query.getSingleResult().getCreatedAt());
            user.setUsername(query.getSingleResult().getUsername());
            return Optional.of(user);
        } catch (NoResultException e) {
            return Optional.empty();
        } finally {
            em.close(); 
        }
    }    
    public Optional<UserDTO> findById(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            User userEntity = em.find(User.class, id);
            if (userEntity != null) {
                UserDTO user = new UserDTO();
                user.setId(userEntity.getId());
                user.setName(userEntity.getName());
                user.setUsername(userEntity.getUsername());
                user.setEmail(userEntity.getEmail());
                user.setPassword(userEntity.getPassword());
                user.setBirthDate(userEntity.getBirthDate());
                user.setCreditLimit(userEntity.getCreditLimit());
                user.setPhoneNumber(userEntity.getPhoneNumber());
                user.setJob(userEntity.getJob());
                return Optional.of(user);
            }
        } catch (Exception e) {
            System.out.println("Failed to find user: " + e.getMessage());
        } finally {
            em.close();
        }
        return Optional.empty();
    }
    
    public List<User> getAllUsers() throws SQLException {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT u FROM User u", User.class).getResultList();
        } finally {
            em.close();
        }
    }
    
}
