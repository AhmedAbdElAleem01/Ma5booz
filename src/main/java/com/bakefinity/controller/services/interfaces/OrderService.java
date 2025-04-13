package com.bakefinity.controller.services.interfaces;

import java.sql.SQLException;
import java.util.List;
import com.bakefinity.model.dtos.OrderDTO;
import com.bakefinity.model.dtos.OrderItemDTO;
import com.bakefinity.model.entities.Order;
import com.bakefinity.model.enums.OrderStatus;

public interface OrderService {
    int create(OrderDTO order) throws SQLException;
    boolean updateStatus(int orderId, OrderStatus orderStatus) throws SQLException;
    List<OrderDTO> getAllOrdersByCustomerId(int customerId);
    List<OrderItemDTO> getOrderItemByOrderId(int orderId);
    
} 
