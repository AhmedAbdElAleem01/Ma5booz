package com.bakefinity.controller.services.impls;

import com.bakefinity.controller.repositories.impls.OrderRepoImpl;
import com.bakefinity.controller.repositories.interfaces.OrderRepo;
import com.bakefinity.controller.services.interfaces.OrderService;
import com.bakefinity.model.entities.Order;
import com.bakefinity.model.enums.OrderStatus;

import java.sql.SQLException;

public class OrderServiceImpl implements OrderService {
    OrderRepo orderRepo = new OrderRepoImpl();
    private static OrderServiceImpl instance;

    public static OrderServiceImpl getInstance(){
        if(instance == null){
            instance = new OrderServiceImpl();
        }
        return instance;
    }

    @Override
    public int create(Order order) throws SQLException {
        return orderRepo.create(order);
    }

    @Override
    public boolean updateStatus(int orderId, OrderStatus orderStatus) throws SQLException{
        return orderRepo.updateStatus(orderId, orderStatus);
    }
}
