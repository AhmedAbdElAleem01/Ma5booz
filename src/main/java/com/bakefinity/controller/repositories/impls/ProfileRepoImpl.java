package com.bakefinity.controller.repositories.impls;

import java.util.Optional;
import com.bakefinity.controller.repositories.interfaces.ProfileRepo;
import com.bakefinity.model.dtos.*;
import com.bakefinity.model.entities.*;
import com.bakefinity.utils.EntityManagerFactorySingleton;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

public class ProfileRepoImpl implements ProfileRepo{  
    private EntityManagerFactory emf = EntityManagerFactorySingleton.getInstance();
    
    @Override
    public Optional<UserDTO> updateCreditLimit(UserDTO user, double creditLimit) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        try {
            tx.begin();
            User retrievedUser = em.find(User.class, user.getId());
            if(retrievedUser!=null){
                retrievedUser.setCreditLimit(creditLimit);
                em.merge(retrievedUser);
                tx.commit();
                user.setCreditLimit(creditLimit); //update
                return Optional.of(user);
            }else{
                System.out.println("DB ERROR: Failed to update user's credit limit");
                tx.rollback();
                return Optional.empty();
            }
        } catch (Exception e) {
            System.out.println("DB ERROR: Failed to update user's credit limit: " + e.getMessage());
        }finally{
            em.close();
        }
        return Optional.empty(); 
    }

    @Override
    public Optional<AddressDTO> updateShippingAddress(UserDTO user, String country, String city, String street, String BNo) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        try{
            tx.begin();
            TypedQuery<Address> query = em.createQuery(
            "SELECT a FROM Address a WHERE a.user.id = :userId", Address.class);
            query.setParameter("userId", user.getId());

            Address address = query.getSingleResult();
            address.setCountry(country);
            address.setCity(city);
            address.setStreet(street);
            address.setBuildingNo(Integer.parseInt(BNo));
            em.merge(address); 
            tx.commit();

            AddressDTO dto = new AddressDTO();
            dto.setCountry(address.getCountry());
            dto.setCity(address.getCity());
            dto.setStreet(address.getStreet());
            dto.setBuildingNo(address.getBuildingNo());
            return Optional.of(dto);
        } catch (Exception e) {
            tx.rollback();
            System.out.println("DB ERROR: Failed to update user's shipping address: " + e.getMessage());
            return Optional.empty();
        } finally{
            em.close();
        }
    }

    @Override
    public Optional<UserDTO> updateAccountDetails(UserDTO user, String name, String job, String email) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try{
            tx.begin();
            TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.id= :userId", User.class);
            query.setParameter("userId", user.getId());
            User myUser = query.getSingleResult();  
            myUser.setUsername(name); 
            myUser.setEmail(email);
            myUser.setJob(job);
            em.merge(myUser);
            tx.commit();
            
            user.setUsername(name); 
            user.setEmail(email);
            user.setJob(job);
            return Optional.of(user);
        } catch (Exception e) {
            tx.rollback();
            System.out.println("DB ERROR: Failed to update user's account details: " + e.getMessage());
            return Optional.empty(); 
        }finally{
            em.close();
        }
    }

    @Override
    public Optional<UserDTO> updatePassword(UserDTO user, String newPass) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        try{
            tx.begin();
            TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.id= :userId", User.class);
            query.setParameter("userId", user.getId());
            User myUser = query.getSingleResult();
            myUser.setPassword(newPass);
            em.merge(myUser);
            tx.commit();
            user.setPassword(newPass);
            return Optional.of(user);
        } catch (Exception e) {
            tx.rollback();
            System.out.println("DB ERROR: Failed to update user's account details: " + e.getMessage());
            return Optional.empty(); 
        }
    }

    public boolean isUsernameTaken(String name) {
        EntityManager em = emf.createEntityManager();
        try {
            Long count = em.createQuery(
                "SELECT COUNT(u) FROM User u WHERE u.username = :username", Long.class
            ).setParameter("username", name).getSingleResult();
            return count > 0;
        } catch (Exception e) {
            System.out.println("DB ERROR: Failed to validate new username " + e.getMessage());
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public Optional<UserDTO> updatePhoneNumber(UserDTO user, String mobile) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try{
            tx.begin();
            TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.id=:userId" , User.class);
            query.setParameter("userId", user.getId());

            User myUser = query.getSingleResult();
            myUser.setPhoneNumber(mobile);
            em.merge(myUser);
            tx.commit();
            user.setPhoneNumber(mobile);
            return Optional.of(user);
        } catch (Exception e) {
            tx.rollback();
            System.out.println("DB ERROR: Failed to update user's phone number: " + e.getMessage());
            return Optional.empty(); 
        }
    }   
    
}
