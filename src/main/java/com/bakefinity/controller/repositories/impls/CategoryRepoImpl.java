package com.bakefinity.controller.repositories.impls;

import com.bakefinity.controller.repositories.interfaces.CategoryRepo;
import com.bakefinity.model.entities.Category;
import com.bakefinity.model.entities.User;
import com.bakefinity.model.dtos.CategoryDTO;
import com.bakefinity.utils.ConnectionManager;
import com.bakefinity.utils.EntityManagerFactorySingleton;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepoImpl implements CategoryRepo {
    private EntityManagerFactory emf = EntityManagerFactorySingleton.getInstance();
    @Override
    public Category get(int id) throws Exception {
        EntityManager em = emf.createEntityManager();
        try{
            Category category = em.find(Category.class, id);
            if(category!=null){
                return category;
            }
        }finally{
            em.close();
        }
        return null;
    }
    @Override
    public List<Category> getAll() throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT cat FROM Category cat", Category.class).getResultList();
        } finally {
            em.close();
        }
    }
    @Override
    public List<Category> searchByName(String name) throws Exception {
        EntityManager em = emf.createEntityManager();        
        try{
            String query = "SELECT c FROM Category c WHERE c.name LIKE :name";
            return em.createQuery(query, Category.class)
                    .setParameter("name", "%" + name + "%")
                    .getResultList();
        }finally{
            em.close();
        }
    }
    @Override
    public CategoryDTO getCategoryByName(String categoryName) throws SQLException{
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Category> query = em.createQuery(
                "SELECT c FROM Category c WHERE c.name = :name", Category.class);
            query.setParameter("name", categoryName);
            Category category = query.getSingleResult();

            return new CategoryDTO(
                category.getId(),
                category.getName(),
                category.getDescription(),
                category.getImageUrl()
            );
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

}