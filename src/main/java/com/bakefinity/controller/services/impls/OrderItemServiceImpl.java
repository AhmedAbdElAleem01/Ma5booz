package com.bakefinity.controller.services.impls;

import com.bakefinity.controller.repositories.impls.OrderItemRepoImpl;
import com.bakefinity.controller.repositories.interfaces.OrderItemRepo;
import com.bakefinity.controller.services.interfaces.OrderItemService;
import com.bakefinity.model.entities.OrderItem;

import java.sql.SQLException;

public class OrderItemServiceImpl implements OrderItemService {
    OrderItemRepo orderItemRepo = new OrderItemRepoImpl();
    private static OrderItemServiceImpl instance;

    public static synchronized OrderItemServiceImpl getInstance(){
        if(instance == null){
            instance = new OrderItemServiceImpl();
        }
        return instance;
    }

    @Override
    public boolean create(OrderItem orderItem) throws SQLException {
        return orderItemRepo.create(orderItem);
    }
}
