package com.bakefinity.controller.repositories.impls;

import com.bakefinity.controller.repositories.interfaces.OrderRepo;
import com.bakefinity.model.entities.Order;
import com.bakefinity.model.enums.OrderStatus;
import com.bakefinity.utils.ConnectionManager;
import java.sql.*;

public class OrderRepoImpl implements OrderRepo {
    @Override
    public int create(Order order) throws SQLException {
        if (order == null) {
            System.err.println("Error creating order: order is null");
            return -1;
        }
        try(Connection connection = ConnectionManager.getConnection();) {
            String query = "INSERT INTO orders (userId, totalCost, paymentMethod, status) VALUES (?, ?, ?, ?)";
            try(PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);) {
                statement.setInt(1, order.getUserId());
                statement.setDouble(2, order.getTotalCost());
                statement.setString(3, String.valueOf(order.getPaymentMethod()));
                statement.setString(4, String.valueOf(order.getStatus()));
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected <= 0) {
                    System.err.println("Failed to create order");
                    return -1;
                } else {
                    System.out.println("order is created successfully");
                    try (ResultSet resultSet = statement.getGeneratedKeys()) {
                        if (resultSet.next()) {
                            int newOrderId = resultSet.getInt(1); // getGeneratedKeys() -> "id" (wrong), 1 (right)
                            System.out.println("order created successfully with ID: " + newOrderId);
                            return newOrderId;
                        }
                    }
                    System.err.println("Failed to retrieve order ID");
                    return -1;
                }
            }
        }
    }

    @Override
    public boolean updateStatus(int orderId, OrderStatus orderStatus) throws SQLException{
        String query = "UPDATE orders SET status = ? WHERE id = ?";
        try(Connection connection = ConnectionManager.getConnection();) {
            try(PreparedStatement statement = connection.prepareStatement(query);) {
                statement.setString(1, String.valueOf(orderStatus));
                statement.setInt(2, orderId);
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("order status is updated successfully");
                    return true;
                }
                return false;
            }
        }
    }
}