package com.bakefinity.controller.repositories.impls;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.bakefinity.controller.repositories.interfaces.OrderRepo;
import com.bakefinity.model.dtos.OrderItemDTO;
import com.bakefinity.model.entities.Order;
import com.bakefinity.model.entities.OrderItem;
import com.bakefinity.model.enums.OrderStatus;
import com.bakefinity.utils.ConnectionManager;

public class OrderRepoImpl implements OrderRepo{

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
    public List<Order> getAll() throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public void add(Order t) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }

    @Override
    public void update(Order t) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(int id) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
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
                orderItem.setId(rs.getInt("id"));
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
