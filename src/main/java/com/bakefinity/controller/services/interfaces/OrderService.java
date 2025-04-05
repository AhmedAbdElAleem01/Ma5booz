package com.bakefinity.controller.services.interfaces;

import com.bakefinity.model.entities.Order;
import com.bakefinity.model.enums.OrderStatus;

import java.sql.SQLException;

public interface OrderService {
    int create(Order order) throws SQLException;
    boolean updateStatus(int orderId, OrderStatus orderStatus) throws SQLException;
}