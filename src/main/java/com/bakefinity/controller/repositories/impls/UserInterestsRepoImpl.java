package com.bakefinity.controller.repositories.impls;

import com.bakefinity.controller.repositories.interfaces.UserInterestsRepo;
import com.bakefinity.model.dtos.UserInterests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserInterestsRepoImpl implements UserInterestsRepo {
    String url = "jdbc:mysql://localhost:3306/ecommercedb";
    String username = "root";
    String password = "root";

    @Override
    public boolean createUserInterests(UserInterests userInterests) throws SQLException {
        if (userInterests == null) {
            System.err.println("Error creating userInterests: UserInterests is null");
            return false;
        }
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try(Connection connection = DriverManager.getConnection(url, username, password);) {
            String query = "INSERT INTO UserInterests (userId, categoryId) VALUES (?, ?)";
            try(PreparedStatement statement = connection.prepareStatement(query);) {
                statement.setInt(1, userInterests.getUserId());
                statement.setInt(2, userInterests.getCategoryId());
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected <= 0) {
                    System.err.println("Failed to create userInterests");
                    return false;
                } else {
                    System.out.println("userInterests is created successfully");
                    return true;
                }
            }
        }
    }
}
