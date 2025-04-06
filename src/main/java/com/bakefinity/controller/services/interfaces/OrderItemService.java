package com.bakefinity.controller.services.interfaces;

import com.bakefinity.model.entities.OrderItem;

import java.sql.SQLException;

public interface OrderItemService {
    boolean create(OrderItem orderItem) throws SQLException;
}
