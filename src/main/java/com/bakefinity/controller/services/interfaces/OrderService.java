package com.bakefinity.controller.services.interfaces;

import java.util.List;

import com.bakefinity.model.dtos.OrderDTO;
import com.bakefinity.model.dtos.OrderItemDTO;

public interface OrderService {
    List<OrderDTO> getAllOrdersByCustomerId(int customerId);
    List<OrderItemDTO> getOrderItemByOrderId(int orderId);
    
} 
