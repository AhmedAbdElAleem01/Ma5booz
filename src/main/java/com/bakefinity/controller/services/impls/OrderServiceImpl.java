package com.bakefinity.controller.services.impls;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.bakefinity.controller.repositories.impls.OrderRepoImpl;
import com.bakefinity.controller.repositories.impls.UserRepoImpl;
import com.bakefinity.controller.repositories.interfaces.OrderRepo;
import com.bakefinity.controller.repositories.interfaces.UserRepo;
import com.bakefinity.controller.services.interfaces.OrderService;
import com.bakefinity.model.dtos.OrderDTO;
import com.bakefinity.model.dtos.OrderItemDTO;
import com.bakefinity.model.dtos.UserDTO;
import com.bakefinity.model.entities.Order;

public class OrderServiceImpl implements OrderService{
    private static OrderServiceImpl instance;
    UserRepo userRepo;
    OrderRepo orderRepo;

    private OrderServiceImpl(){
        userRepo = new UserRepoImpl();
        orderRepo = new OrderRepoImpl();
    }
    public static OrderService getInstance() {
        if (instance == null) {
            synchronized (OrderServiceImpl.class) {
                if (instance == null) {
                    instance = new OrderServiceImpl();
                }
            }
        }
        return instance;
    }
    @Override
    public List<OrderDTO> getAllOrdersByCustomerId(int customerId){
        Optional<UserDTO> customer = userRepo.findById(customerId);
        if(customer.isEmpty())
            return new ArrayList<>();
        try {
            List<Order> orders = orderRepo.getOrdersByCustomerId(customerId);
            List<OrderDTO> orderDTOs = new ArrayList<>();
            for (Order order : orders) {
                OrderDTO dto = new OrderDTO();
                dto.setId(order.getId());
                dto.setTotalCost(order.getTotalCost());
                dto.setPaymentMethod(order.getPaymentMethod());
                dto.setOrderedAt(order.getOrderedAt());
                dto.setStatus(order.getStatus());            
                orderDTOs.add(dto);
            }            
            return orderDTOs;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get customer's past orders: " + e.getMessage());
        }
    }
    @Override
    public List<OrderItemDTO> getOrderItemByOrderId(int orderId) {
        Order order;
        try {
            order = orderRepo.get(orderId);
            if(order==null) 
                return new ArrayList<>();   // order does not exist
            return orderRepo.getOrderItemsByOrderId(orderId);
        } catch (Exception e) {
            throw new RuntimeException("Failed to get order's items: " + e.getMessage());
        }
    }
    
}
