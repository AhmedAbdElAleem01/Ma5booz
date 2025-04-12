package com.bakefinity.controller.repositories.impls;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.bakefinity.controller.repositories.interfaces.OrderRepo;
import com.bakefinity.model.dtos.OrderDTO;
import com.bakefinity.model.dtos.OrderItemDTO;
import com.bakefinity.model.entities.Order;
import com.bakefinity.model.enums.OrderStatus;
import com.bakefinity.utils.ConnectionManager;

public class OrderRepoImpl implements OrderRepo{
    
    @Override
    public int create(OrderDTO order) throws SQLException {
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

    @Override
    public Order get(int id) throws Exception { 
        try (Connection conn = ConnectionManager.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM orders WHERE id=?")) {
            
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if(rs.next()) {
                    Order order = new Order();
                    order.setId(rs.getInt("id"));
                    order.setTotalCost(rs.getDouble("totalCost"));
                    order.setOrderedAt(rs.getTimestamp("orderedAt").toLocalDateTime());
                    order.setStatus(OrderStatus.valueOf(rs.getString("status")));
                    return order;
                }
            }
            return null;
        }
    }
    @Override
    public List<Order> getOrdersByCustomerId(int customerId) throws SQLException {
        List<Order> orders = new ArrayList<>();

        String query = "SELECT * FROM orders WHERE userId=?";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);){

            pstmt.setInt(1, customerId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setTotalCost(rs.getDouble("totalCost"));
                order.setOrderedAt(rs.getTimestamp("orderedAt").toLocalDateTime());
                order.setStatus(OrderStatus.valueOf(rs.getString("status")));
                orders.add(order);
            }
        }
        System.out.println("From DB: Orders of " + customerId + " : " + orders);
        return orders;
    }

    @Override
    public List<OrderItemDTO> getOrderItemsByOrderId(int orderId) {
        List<OrderItemDTO> orderItems = new ArrayList<>();
        
        String query = "SELECT oi.* , oi.quantity, p.price, p.name AS productName " +
                        "FROM orderitem oi " +
                        "JOIN product p ON oi.productId = p.id " +
                        "WHERE oi.orderId = ?";
        
        try (Connection conn = ConnectionManager.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                OrderItemDTO orderItem = new OrderItemDTO();
//                orderItem.setId(rs.getInt("id"));
                orderItem.setOrderId(rs.getInt("orderId"));
                orderItem.setProductId(rs.getInt("productId"));
                orderItem.setProductName(rs.getString("productName"));
                orderItem.setPrice(rs.getDouble("price"));
                orderItem.setQuantity(rs.getInt("quantity"));
                
                orderItems.add(orderItem);
            }
        } catch (SQLException e) {
            new RuntimeException("Failed to retreive order items: " + e.getMessage());
        }
        return orderItems;
    }

}
