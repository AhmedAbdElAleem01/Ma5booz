package com.bakefinity.controller.repositories.impls;

import com.bakefinity.controller.repositories.interfaces.CartRepo;
import com.bakefinity.model.entities.CartItem;
import com.bakefinity.model.entities.CartItemId;
import com.bakefinity.utils.ConnectionManager;
import com.bakefinity.utils.EntityManagerFactorySingleton;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartRepoImpl implements CartRepo {
    private EntityManagerFactory emf = EntityManagerFactorySingleton.getInstance();

    @Override
    public CartItem get(int userId, int productId) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<CartItem> query = em.createQuery(
                "SELECT c FROM CartItem c WHERE c.user.id = :userId AND c.product.id = :productId", 
                CartItem.class);
            query.setParameter("userId", userId);
            query.setParameter("productId", productId);

            return query.getResultStream().findFirst().orElse(null);
        } finally {
            em.close();
        }
    }

    @Override
    public List<CartItem> getAllByUserId(int userId) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<CartItem> query = em.createQuery(
                "SELECT c FROM CartItem c WHERE c.user.id = :userId", CartItem.class);
            query.setParameter("userId", userId);

            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public void add(CartItem cartItem) throws Exception {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try{
            tx.begin();
            CartItem existingItem = em.createQuery(
                "SELECT c FROM CartItem c WHERE c.user.id = :userId AND c.product.id = :productId", 
                CartItem.class)
                .setParameter("userId", cartItem.getId().getUserId())
                .setParameter("productId", cartItem.getId().getProductId())
                .getResultStream()
                .findFirst()
                .orElse(null);
            if (existingItem != null) {
                existingItem.setQuantity(cartItem.getQuantity()); //update quantity
                em.merge(existingItem);
            } else {
                em.persist(cartItem);
            }
            tx.commit();
        }catch(Exception e){
            tx.rollback();
            System.out.println("DB Error: Could not add to cart : "+e.getMessage());
        }finally{
            em.close();
        }
    }

    @Override
    public void update(CartItem cartItem) throws Exception {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try{
            tx.begin();
            CartItem existingItem = em.createQuery(
            "SELECT c FROM CartItem c WHERE c.user.id = :userId AND c.product.id = :productId",
            CartItem.class)
            .setParameter("userId", cartItem.getId().getUserId())
            .setParameter("productId", cartItem.getId().getProductId())
            .getSingleResult();

        existingItem.setQuantity(cartItem.getQuantity());

        em.merge(existingItem);
        tx.commit();
        } catch (NoResultException e) {
            System.err.println("CartItem not found for update.");
        } catch (Exception e) {
            tx.rollback();
            System.out.println("DB Error: Could not update cart: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public void clearUserCart(int userId) throws Exception {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.createQuery("DELETE FROM CartItem c WHERE c.user.id = :userId")
            .setParameter("userId", userId)
            .executeUpdate();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            System.out.println("DB Error: Could not clear user cart: "+e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(int userId, int productId) throws Exception {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            CartItemId id = new CartItemId(userId, productId);
            CartItem cartItem = em.find(CartItem.class, id);
            if (cartItem != null) {
                em.remove(cartItem);
            }
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}
