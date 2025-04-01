package com.bakefinity.controller.repositories.impls;

import com.bakefinity.controller.repositories.interfaces.CategoryRepo;
import com.bakefinity.model.entities.Category;
import com.bakefinity.model.dtos.CategoryDTO;
import com.bakefinity.utils.ConnectionManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepoImpl implements CategoryRepo {

    @Override
    public Category get(int id) throws Exception {
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM category WHERE id = ?")) {
            
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return extractCategory(rs);
                }
            }
            return null;
        }
    }

    private Category extractCategory(ResultSet rs) throws SQLException {
        Category category = new Category();
        category.setId(rs.getInt("id"));
        category.setName(rs.getString("name"));
        category.setDescription(rs.getString("description"));
        category.setImageUrl(rs.getString("imageUrl"));
        return category;
    }

    @Override
    public List<Category> getAll() throws Exception {
        List<Category> categories = new ArrayList<>();
        
        try (Connection conn = ConnectionManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM category")) {
            
            while (rs.next()) {
                categories.add(extractCategory(rs));
            }
        }
        return categories;
    }

    @Override
    public void add(Category category) throws Exception {
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                 "INSERT INTO category (name, description, imageUrl) VALUES (?, ?, ?)",
                 Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, category.getName());
            stmt.setString(2, category.getDescription());
            stmt.setString(3, category.getImageUrl());
            
            int affectedRows = stmt.executeUpdate();
            
            if (affectedRows == 0) {
                throw new SQLException("Creating category failed, no rows affected.");
            }
            
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    category.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    @Override
    public void update(Category category) throws Exception {
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                 "UPDATE category SET name = ?, description = ?, imageUrl = ? WHERE id = ?")) {
            
            stmt.setString(1, category.getName());
            stmt.setString(2, category.getDescription());
            stmt.setString(3, category.getImageUrl());
            stmt.setInt(4, category.getId());
            
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM category WHERE id = ?")) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }


    @Override
    public List<Category> searchByName(String name) throws Exception {
        List<Category> categories = new ArrayList<>();
        
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                 "SELECT * FROM category WHERE name LIKE ?")) {
            
            stmt.setString(1, "%" + name + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    categories.add(extractCategory(rs));
                }
            }
        }
        return categories;
    }


    @Override
    public boolean createCategory(CategoryDTO category) throws SQLException {
        if (category == null) {
            System.err.println("Error creating category: Category is null");
            return false;
        }
        try(Connection connection = ConnectionManager.getConnection()) {
            String query = "INSERT INTO Category (name, description, imageUrl) VALUES (?, ?, ?)";
            try(PreparedStatement statement = connection.prepareStatement(query);) {
                statement.setString(1, category.getName());
                statement.setString(2, category.getDescription());
                statement.setString(3, category.getImageUrl());
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected <= 0) {
                    System.err.println("Failed to create category");
                    return false;
                } else {
                    System.out.println("category is created successfully");
                    return true;
                }
            }
        }
    }

    @Override
    public CategoryDTO getCategoryByName(String categoryName) throws SQLException{
        try(Connection connection = ConnectionManager.getConnection()) {
            String query = "SELECT id, name, description, imageUrl FROM Category WHERE name = ?";
            try(PreparedStatement statement = connection.prepareStatement(query);) {
                statement.setString(1, categoryName);
                try(ResultSet resultSet = statement.executeQuery();) {
                    if (resultSet.next()) {
                        return new CategoryDTO(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("description"), resultSet.getString("imageUrl"));
                    } else {
                        return null;
                    }
                }
            }
        }
    }

}