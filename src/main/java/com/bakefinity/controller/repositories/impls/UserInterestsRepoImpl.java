package com.bakefinity.controller.repositories.impls;

import com.bakefinity.controller.repositories.interfaces.UserInterestsRepo;
import com.bakefinity.model.dtos.UserInterestsDTO;
import com.bakefinity.utils.ConnectionManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserInterestsRepoImpl implements UserInterestsRepo {
    @Override
    public boolean createUserInterests(UserInterestsDTO userInterests) throws SQLException {
        if (userInterests == null) {
            System.err.println("Error creating userInterests: UserInterests is null");
            return false;
        }
        try(Connection connection = ConnectionManager.getConnection();) {
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
