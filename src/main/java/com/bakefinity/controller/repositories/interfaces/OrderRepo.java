package com.bakefinity.controller.repositories.interfaces;

import java.sql.SQLException;
import java.util.List;

import com.bakefinity.model.dtos.OrderItemDTO;
import com.bakefinity.model.entities.Order;

public interface OrderRepo extends BaseRepo<Order>{
    List<Order> getOrdersByCustomerId(int customerId) throws SQLException;
    List<OrderItemDTO> getOrderItemsByOrderId(int orderId) throws SQLException;
}
