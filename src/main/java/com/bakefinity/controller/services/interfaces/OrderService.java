package com.bakefinity.controller.services.interfaces;

import java.util.List;

import com.bakefinity.model.dtos.OrderItemDTO;
import com.bakefinity.model.entities.Order;

public interface OrderService {
    List<Order> getAllOrdersByCustomerId(int customerId);
    List<OrderItemDTO> getOrderItemByOrderId(int orderId);
    
} 
