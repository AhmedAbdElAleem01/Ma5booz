package com.bakefinity.controller.repositories.impls;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.bakefinity.controller.repositories.interfaces.ProductRepo;
import com.bakefinity.model.dtos.ProductDTO;
import com.bakefinity.model.entities.Product;
import com.bakefinity.utils.ConnectionManager;

public class ProductRepoImpl implements ProductRepo {

    @Override
    public ProductDTO get(int productId) throws Exception {
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

    private ProductDTO extractProduct(ResultSet rs) throws SQLException {
        return new ProductDTO(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getInt("categoryId"),
            rs.getString("description"),
            rs.getDouble("price"),
            rs.getString("imageUrl"),
            rs.getInt("stockQuantity"),
            rs.getString("ingredients")
        );
    }

    @Override
    public List<ProductDTO> getAll() throws Exception {
        List<ProductDTO> products = new ArrayList<>();
        
        try (Connection conn = ConnectionManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM product")) {
            
            while(rs.next()) {
                products.add(extractProduct(rs));
            }
        }
        return products;
    }

    // returns all products with the name of the category they belong in
    @Override
    public List<ProductDTO> getAllProducts() throws Exception {
        List<ProductDTO> products = new ArrayList<>();
        
        String query = "SELECT p.*, c.name AS categoryName " +
                    "FROM product p " +
                    "JOIN category c ON p.categoryId = c.id";
        
        try (Connection conn = ConnectionManager.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                ProductDTO product = new ProductDTO();
                product.setId(rs.getInt("id"));
                product.setCategoryName(rs.getString("categoryName"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getDouble("price"));
                product.setImageUrl(rs.getString("imageUrl"));
                product.setStockQuantity(rs.getInt("stockQuantity"));
                
                products.add(product);
            }
        }
        return products;
    }
    @Override
    public void add(ProductDTO product) throws Exception {
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
    public void update(ProductDTO product) throws Exception {
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
    public List<ProductDTO> getByCategory(int categoryId) throws Exception {
        List<ProductDTO> products = new ArrayList<>();
        
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
    public List<ProductDTO> getTopInStock(int limit) throws Exception {
        List<ProductDTO> products = new ArrayList<>();
        
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
    public List<ProductDTO> searchByName(String name) throws Exception {
        List<ProductDTO> products = new ArrayList<>();
    
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

    public List<ProductDTO> getProductsByCategoryPage(int categoryId, int offset, int limit) {
        String sql = "SELECT * FROM product WHERE categoryId = ? LIMIT ? OFFSET ?";
        List<ProductDTO> products = new ArrayList<>();

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
    public List<ProductDTO> getProductsByPage(int offset, int limit) {
        String sql = "SELECT * FROM product LIMIT ? OFFSET ?";
        List<ProductDTO> products = new ArrayList<>();
    
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

    @Override
    public boolean updateStockQuantity(int productId, int newQuantity) throws SQLException {
        String query = "UPDATE Product SET stockQuantity = ? WHERE id = ?";
        try(Connection connection = ConnectionManager.getConnection();) {
            try(PreparedStatement statement = connection.prepareStatement(query);) {
                statement.setInt(1, newQuantity);
                statement.setInt(2, productId);
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("stock quantity is updated successfully");
                    return true;
                }
                return false;
            }
        }
    }

    @Override
    public ProductDTO getById(int productId) throws SQLException{
        String query = "SELECT * FROM Product WHERE id = ?";
        try(Connection connection = ConnectionManager.getConnection();) {
            try(PreparedStatement statement = connection.prepareStatement(query);) {
                statement.setInt(1, productId);
                try(ResultSet resultSet = statement.executeQuery();) {
                    if (resultSet.next()) {
                        return new ProductDTO(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getInt("categoryId"), resultSet.getString("description"), resultSet.getDouble("price"), resultSet.getString("imageUrl"), resultSet.getInt("stockQuantity"), resultSet.getString("ingredients"));
                    }
                    return null;
                }
            }
        }
    }


    public List<ProductDTO> getProductsByCategoryAndPriceRange(int categoryId, double minPrice, double maxPrice, int offset, int limit) {
        String sql = "SELECT * FROM product WHERE categoryId = ? AND price BETWEEN ? AND ? ORDER BY price ASC LIMIT ? OFFSET ?";
        List<ProductDTO> products = new ArrayList<>();
    
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
    
            stmt.setInt(1, categoryId);
            stmt.setDouble(2, minPrice);
            stmt.setDouble(3, maxPrice);
            stmt.setInt(4, limit);
            stmt.setInt(5, offset);
    
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    products.add(extractProduct(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return products;
    }
    

    public List<ProductDTO> getProductsByPriceRange(int offset, int limit, double minPrice, double maxPrice) {
        String sql = "SELECT * FROM product WHERE price BETWEEN ? AND ? ORDER BY price ASC LIMIT ? OFFSET ?";
        List<ProductDTO> products = new ArrayList<>();
    
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
    
            stmt.setDouble(1, minPrice);
            stmt.setDouble(2, maxPrice);
            stmt.setInt(3, limit);
            stmt.setInt(4, offset);
    
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    products.add(extractProduct(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return products;
    }
    
    
    
}