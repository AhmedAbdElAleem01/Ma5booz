package com.bakefinity.controller.repositories.impls;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.bakefinity.controller.repositories.interfaces.ProductRepo;
import com.bakefinity.model.entities.Product;
import com.bakefinity.utils.ConnectionManager;

public class ProductRepoImpl implements ProductRepo {

    @Override
    public Product get(int productId) throws Exception {
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM product WHERE id=?")) {
            
            stmt.setInt(1, productId);
            try (ResultSet rs = stmt.executeQuery()) {
                if(rs.next()) {
                    return extractProduct(rs);
                }
            }
            return null;
        }
    }

    private Product extractProduct(ResultSet rs) throws SQLException {
        return new Product(
            rs.getInt("id"),
            rs.getInt("categoryId"),
            rs.getString("name"),
            rs.getString("description"),
            rs.getDouble("price"),
            rs.getString("imageUrl"),
            rs.getInt("stockQuantity"),
            rs.getString("ingredients")
        );
    }

    @Override
    public List<Product> getAll() throws Exception {
        List<Product> products = new ArrayList<>();
        
        try (Connection conn = ConnectionManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM product")) {
            
            while(rs.next()) {
                products.add(extractProduct(rs));
            }
        }
        return products;
    }

    @Override
    public void add(Product product) throws Exception {
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                 "INSERT INTO product (categoryId, name, description, price, " +
                 "imageUrl, stockQuantity, ingredients) VALUES (?, ?, ?, ?, ?, ?, ?)",
                 Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setInt(1, product.getCategoryId());
            stmt.setString(2, product.getName());
            stmt.setString(3, product.getDescription());
            stmt.setDouble(4, product.getPrice());
            stmt.setString(5, product.getImageUrl());
            stmt.setInt(6, product.getStockQuantity());
            stmt.setString(7, product.getIngredients());
            
            int affectedRows = stmt.executeUpdate();
            
            if (affectedRows == 0) {
                throw new SQLException("Creating product failed, no rows affected.");
            }
        }
    }

    @Override
    public void update(Product product) throws Exception {
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                 "UPDATE product SET categoryId=?, name=?, description=?, price=?, " +
                 "imageUrl=?, stockQuantity=?, ingredients=? WHERE id=?")) {
            
            stmt.setInt(1, product.getCategoryId());
            stmt.setString(2, product.getName());
            stmt.setString(3, product.getDescription());
            stmt.setDouble(4, product.getPrice());
            stmt.setString(5, product.getImageUrl());
            stmt.setInt(6, product.getStockQuantity());
            stmt.setString(7, product.getIngredients());
            stmt.setInt(8, product.getId());
            
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(int productId) throws Exception {
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM product WHERE id=?")) {
            
            stmt.setInt(1, productId);
            stmt.executeUpdate();
        }
    }

    @Override
    public List<Product> getByCategory(int categoryId) throws Exception {
        List<Product> products = new ArrayList<>();
        
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM product WHERE categoryId=?")) {
            
            stmt.setInt(1, categoryId);
            try (ResultSet rs = stmt.executeQuery()) {
                while(rs.next()) {
                    products.add(extractProduct(rs));
                }
            }
        }
        return products;
    }

    @Override
    public List<Product> getTopInStock(int limit) throws Exception {
        List<Product> products = new ArrayList<>();
        
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM product ORDER BY stockQuantity DESC LIMIT ?")) {
            
            stmt.setInt(1, limit);
            try (ResultSet rs = stmt.executeQuery()) {
                while(rs.next()) {
                    products.add(extractProduct(rs));
                }
            }
        }
        return products;
    }

    @Override
    public List<Product> searchByName(String name) throws Exception {
        List<Product> products = new ArrayList<>();
    
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM product WHERE LOWER(name) LIKE LOWER(?)")) {
    
            stmt.setString(1, "%" + name + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    products.add(extractProduct(rs));
                    System.out.println("add: "+ extractProduct(rs));
                }
            }
        }
        return products;
    }

    public List<Product> getProductsByCategoryPage(int categoryId, int offset, int limit) {
        String sql = "SELECT * FROM product WHERE categoryId = ? LIMIT ? OFFSET ?";
        List<Product> products = new ArrayList<>();

        try (Connection conn = ConnectionManager.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, categoryId);
            stmt.setInt(2, limit);
            stmt.setInt(3, offset);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                products.add(extractProduct(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public int getTotalCountByCategory(int categoryId) {
        String sql = "SELECT COUNT(*) FROM product WHERE categoryId = ?";
        try (Connection conn = ConnectionManager.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, categoryId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Product> getProductsByPage(int offset, int limit) {
        String sql = "SELECT * FROM product LIMIT ? OFFSET ?";
        List<Product> products = new ArrayList<>();
    
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
    
            stmt.setInt(1, limit);
            stmt.setInt(2, offset);
    
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                products.add(extractProduct(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public int getTotalCount() {
        String sql = "SELECT COUNT(*) FROM product";
        try (Connection conn = ConnectionManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
