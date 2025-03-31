package com.bakefinity.controller.repositories.impls;

import com.bakefinity.controller.repositories.interfaces.CategoryRepo;
import com.bakefinity.model.dtos.Category;

import java.sql.*;

public class CategoryRepoImpl implements CategoryRepo {
    String url = "jdbc:mysql://localhost:3306/ecommercedb";
    String username = "root";
    String password = "root";

    @Override
    public boolean createCategory(Category category) throws SQLException {
        if (category == null) {
            System.err.println("Error creating category: Category is null");
            return false;
        }
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try(Connection connection = DriverManager.getConnection(url, username, password);) {
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
    public Category getCategoryByName(String categoryName) throws SQLException{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try(Connection connection = DriverManager.getConnection(url, username, password);) {
            String query = "SELECT id, name, description, imageUrl FROM Category WHERE name = ?";
            try(PreparedStatement statement = connection.prepareStatement(query);) {
                statement.setString(1, categoryName);
                try(ResultSet resultSet = statement.executeQuery();) {
                    if (resultSet.next()) {
                        return new Category(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("description"), resultSet.getString("imageUrl"));
                    } else {
                        return null;
                    }
                }
            }
        }
    }
}