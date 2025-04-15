package com.bakefinity.controller.repositories.impls;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;

import com.bakefinity.controller.repositories.interfaces.ProductRepo;
import com.bakefinity.model.dtos.ProductDTO;
import com.bakefinity.model.entities.Product;
import com.bakefinity.model.entities.Category;
import com.bakefinity.utils.ConnectionManager;
import com.bakefinity.utils.EntityManagerFactorySingleton;

import jakarta.persistence.*;

public class ProductRepoImpl implements ProductRepo {
    private EntityManagerFactory emf = EntityManagerFactorySingleton.getInstance();

    @Override
    public ProductDTO get(int productId) throws Exception {
        EntityManager em = emf.createEntityManager();
        try{
            Product product = em.find(Product.class, productId);
            if(product!=null){
                  return extractProductDTO(product);
                }
            }finally{
                em.close();
            }
            return null;
    }

    // private ProductDTO extractProduct(ResultSet rs) throws SQLException {
    //     return new ProductDTO(
    //         rs.getInt("id"),
    //         rs.getString("name"),
    //         rs.getInt("categoryId"),
    //         rs.getString("description"),
    //         rs.getDouble("price"),
    //         rs.getString("imageUrl"),
    //         rs.getInt("stockQuantity"),
    //         rs.getString("ingredients")
    //     );
    // }
    private ProductDTO extractProductDTO(Product product) throws SQLException {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setCategoryId(product.getCategory().getId());
        dto.setCategoryName(product.getCategory().getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setImageUrl(product.getImageUrl());
        dto.setStockQuantity(product.getStockQuantity());
        dto.setIngredients(product.getIngredients());
        return dto;
    }

    @Override
    public List<ProductDTO> getAll() throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            List<Product> products = em.createQuery("FROM Product", Product.class).getResultList();
            List<ProductDTO> dtos = new ArrayList<>();
            for (Product p : products) dtos.add(extractProductDTO(p));
            return dtos;
        } finally{
            em.close();
        } 
    }

    // returns all products with the name of the category they belong in
    @Override
    public List<ProductDTO> getAllProducts() throws Exception {
        EntityManager em = emf.createEntityManager();
        List<ProductDTO> products = new ArrayList<>();
        
        try{
            List<Product> resultList = em.createQuery(
            "SELECT p FROM Product p JOIN FETCH p.category", Product.class).getResultList();
            for (Product p : resultList) {
                ProductDTO product = new ProductDTO();
                product.setId(p.getId());
                product.setCategoryName(p.getCategory().getName());
                product.setName(p.getName());
                product.setDescription(p.getDescription());
                product.setPrice(p.getPrice());
                product.setImageUrl(p.getImageUrl());
                product.setStockQuantity(p.getStockQuantity());
                
                products.add(product);
            }
        }finally{
            em.close();
        }
        return products;
    }

    
    @Override
    public void add(ProductDTO newProduct) throws Exception {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try{
            tx.begin();
            Product product = new Product();
            Category category = em.getReference(Category.class, newProduct.getCategoryId());
            product.setCategory(category); 
            product.setName(newProduct.getName());
            product.setDescription(newProduct.getDescription());
            product.setPrice(newProduct.getPrice());
            product.setImageUrl(newProduct.getImageUrl());
            product.setStockQuantity(newProduct.getStockQuantity());
            product.setIngredients(newProduct.getIngredients());
            
            em.persist(product);
            tx.commit();
        }catch(Exception e){
            tx.rollback();
            System.out.println("DB Error: Failed to add new product: " + e.getMessage());
        }finally{
            em.close();
        }
    }

    @Override
    public void update(ProductDTO updatedProduct) throws Exception {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try{
            tx.begin();
            Product product= em.find(Product.class, updatedProduct.getId());
            Category category = em.find(Category.class, updatedProduct.getCategoryId());
            if(product!=null){
                product.setCategory(category); 
                product.setName(updatedProduct.getName());
                product.setDescription(updatedProduct.getDescription());
                product.setPrice(updatedProduct.getPrice());
                product.setImageUrl(updatedProduct.getImageUrl());
                product.setStockQuantity(updatedProduct.getStockQuantity());
                product.setIngredients(updatedProduct.getIngredients());
                em.merge(product);
                tx.commit();
            }
        }catch(Exception e){
            tx.rollback();
            System.out.println("DB Error: Failed to update product " + e.getMessage());
        }finally{
            em.close();
        }
    }

    @Override
    public void delete(int productId) throws Exception {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Product product = em.find(Product.class, productId);
            if(product!=null){
                em.remove(product);
            }
            tx.commit();
        }catch(Exception e){
            tx.rollback();
            System.out.println("DB Error: Failed to delete product " + e.getMessage());
        }finally{
            em.close();
        }
    }

    @Override
    public List<ProductDTO> getByCategory(int categoryId) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p WHERE p.category.id = :categoryId", Product.class);
            query.setParameter("categoryId", categoryId);
            List<ProductDTO> dtos = new ArrayList<>();
            for (Product p : query.getResultList()) dtos.add(extractProductDTO(p));
            return dtos;
        } finally {
            em.close();
        }
    }

    @Override
    public List<ProductDTO> getTopInStock(int limit) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p ORDER BY p.stockQuantity DESC", Product.class);
            query.setMaxResults(limit);
            List<ProductDTO> dtos = new ArrayList<>();
            for (Product p : query.getResultList()) dtos.add(extractProductDTO(p));
            return dtos;
        } finally {
            em.close();
        }
    }

    @Override
    public List<ProductDTO> searchByName(String name) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(:name)", Product.class);
            query.setParameter("name", "%" + name + "%");
            List<ProductDTO> dtos = new ArrayList<>();
            for (Product p : query.getResultList()) dtos.add(extractProductDTO(p));
            return dtos;
        } finally {
            em.close();
        }
    }

    public List<ProductDTO> getProductsByCategoryPage(int categoryId, int offset, int limit) {
        EntityManager em = emf.createEntityManager();
        List<ProductDTO> products = new ArrayList<>();
        try {
            List<Product> resultList = em.createQuery(
                "SELECT p FROM Product p JOIN FETCH p.category WHERE p.category.id = :categoryId", Product.class)
                .setParameter("categoryId", categoryId)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    
            for (Product p : resultList) {
                products.add(extractProductDTO(p));
            }
        
        } catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
         finally {
            em.close();
        }
        return products;
    }

    public int getTotalCountByCategory(int categoryId) {
        EntityManager em = emf.createEntityManager();
        try {
            Long count = em.createQuery(
                "SELECT COUNT(p) FROM Product p WHERE p.category.id = :categoryId", Long.class)
                .setParameter("categoryId", categoryId)
                .getSingleResult();
            return count.intValue();
        } finally {
            em.close();
        }
    }

    @Override
    public List<ProductDTO> getProductsByPage(int offset, int limit) {
        EntityManager em = emf.createEntityManager();
        List<ProductDTO> products = new ArrayList<>();
        try {
            List<Product> resultList = em.createQuery("SELECT p FROM Product p", Product.class)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
            for (Product p : resultList) {
                products.add(extractProductDTO(p));
            }
        } catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
        finally {
            em.close();
        }
        return products;
    }

    public int getTotalCount() {
        EntityManager em = emf.createEntityManager();
        try {
            Long count = em.createQuery("SELECT COUNT(p) FROM Product p", Long.class).getSingleResult();
            return count.intValue();
        } finally {
            em.close();
        }
    }

    @Override
    public boolean updateStockQuantity(int productId, int newQuantity) throws SQLException {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Product product = em.find(Product.class, productId);
            if (product == null) {
                System.out.println("there is no product with id = " + product);
                return false;
            }
            product.setStockQuantity(newQuantity);
            em.merge(product);
            em.getTransaction().commit();
            return true;
        }
        finally {
            em.close();
        }
    }

    @Override
    public ProductDTO getById(int productId) throws SQLException{
        try {
            return get(productId);
        } catch (Exception e) {
            throw new SQLException();
        }
    }


    public List<ProductDTO> getProductsByCategoryAndPriceRange(int categoryId, double minPrice, double maxPrice, int offset, int limit) {
        EntityManager em = emf.createEntityManager();
        List<ProductDTO> products = new ArrayList<>();
        try {
            List<Product> resultList = em.createQuery(
                "SELECT p FROM Product p JOIN FETCH p.category WHERE p.category.id = :categoryId AND p.price BETWEEN :minPrice AND :maxPrice ORDER BY p.price ASC",
                Product.class)
                .setParameter("categoryId", categoryId)
                .setParameter("minPrice", minPrice)
                .setParameter("maxPrice", maxPrice)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    
            for (Product p : resultList) {
                products.add(extractProductDTO(p));
            }
        } catch(Exception e){
            throw new RuntimeException(e.getMessage());
        } finally {
            em.close();
        }
        return products;
    }
    

    @Override
    public List<ProductDTO> getProductsByPriceRange(int offset, int limit, double minPrice, double maxPrice) {
        EntityManager em = emf.createEntityManager();
        List<ProductDTO> products = new ArrayList<>();
        try {
            List<Product> resultList = em.createQuery(
                "SELECT p FROM Product p JOIN FETCH p.category WHERE p.price BETWEEN :minPrice AND :maxPrice ORDER BY p.price ASC",
                Product.class)
                .setParameter("minPrice", minPrice)
                .setParameter("maxPrice", maxPrice)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();

            for (Product p : resultList) {
                products.add(extractProductDTO(p));
            }
        } catch(Exception e){
            throw new RuntimeException(e.getMessage());
        } 
        finally {
            em.close();
        }
        return products;
    }

    public int getTotalProductsByPrice(Double minPrice, Double maxPrice, Integer categoryId) {
        EntityManager em = emf.createEntityManager();
        try {
            String query = "SELECT COUNT(p) FROM Product p WHERE p.price BETWEEN :min AND :max";
            if (categoryId != null) {
                query += " AND p.category.id = :catId";
            }
            var q = em.createQuery(query, Long.class)
                .setParameter("min", minPrice)
                .setParameter("max", maxPrice);
    
            if (categoryId != null) {
                q.setParameter("catId", categoryId);
            }
    
            return q.getSingleResult().intValue();
        } finally {
            em.close();
        }
    }
    
}

    
    
    
    
