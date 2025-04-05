package com.bakefinity.controller.repositories.impls;

import com.bakefinity.controller.repositories.interfaces.OrderItemRepo;
import com.bakefinity.model.dtos.UserInterestsDTO;
import com.bakefinity.model.entities.OrderItem;
import com.bakefinity.utils.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderItemRepoImpl implements OrderItemRepo {
    @Override
    public boolean create(OrderItem orderItem) throws SQLException {
        if (orderItem == null) {
            System.err.println("Error creating orderItem: OrderItem is null");
            return false;
        }
        try(Connection connection = ConnectionManager.getConnection();) {
            String query = "INSERT INTO OrderItem (orderId, productId, quantity) VALUES (?, ?, ?)";
            try(PreparedStatement statement = connection.prepareStatement(query);) {
                statement.setInt(1, orderItem.getOrderId());
                statement.setInt(2, orderItem.getProductId());
                statement.setInt(3, orderItem.getQuantity());
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected <= 0) {
                    System.err.println("Failed to create orderItem");
                    return false;
                } else {
                    System.out.println("orderItem is created successfully");
                    return true;
                }
            }
        }
    }
}
