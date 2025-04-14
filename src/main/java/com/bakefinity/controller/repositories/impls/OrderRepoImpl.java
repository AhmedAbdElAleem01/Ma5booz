package com.bakefinity.controller.repositories.impls;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.bakefinity.controller.repositories.interfaces.OrderRepo;
import com.bakefinity.model.dtos.OrderDTO;
import com.bakefinity.model.dtos.OrderItemDTO;
import com.bakefinity.model.entities.Order;
import com.bakefinity.model.entities.OrderItem;
import com.bakefinity.model.entities.User;
import com.bakefinity.model.enums.OrderStatus;
import com.bakefinity.utils.EntityManagerFactorySingleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class OrderRepoImpl implements OrderRepo{
    private EntityManagerFactory emf = EntityManagerFactorySingleton.getInstance();
    @Override
    public int create(OrderDTO orderDTO) throws SQLException {
        if (orderDTO == null) {
            System.err.println("Error creating order: order is null");
            return -1;
        }
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            User user = em.find(User.class, orderDTO.getUserId());
            if(user == null){
                System.out.println("there is no user with id = " + orderDTO.getUserId());
                return -1;
            }
            Order order = new Order(user, orderDTO.getTotalCost(), orderDTO.getPaymentMethod(), orderDTO.getOrderedAt(), orderDTO.getStatus());
            em.persist(order);
            em.getTransaction().commit();
            return order.getId();
        }
        finally {
            em.close();
        }
    }

    @Override
    public boolean updateStatus(int orderId, OrderStatus orderStatus) throws SQLException{
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Order order = em.find(Order.class, orderId);
            if (order == null) {
                System.out.println("There is no order with id = " + order);
                return false;
            }
            order.setStatus(orderStatus);
            em.getTransaction().commit();
            return true;
        }
        finally {
            em.close();
        }
    }

    @Override
    public Order get(int id) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Order order = em.find(Order.class, id);
            if (order == null) {
                System.out.println("there is no order with id = " + id);
                return null;
            }
            em.getTransaction().commit();
            return order;
        }
        finally {
            em.close();
        }
    }

    @Override
    public List<Order> getOrdersByCustomerId(int customerId) throws SQLException {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery(
                "SELECT o FROM Order o WHERE o.user.id = :userId", Order.class)
                .setParameter("userId", customerId)
                .getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<OrderItemDTO> getOrderItemsByOrderId(int orderId) {
        EntityManager em = emf.createEntityManager();
        List<OrderItemDTO> orderItemsDTO = new ArrayList<>();        
        try{
            List<OrderItem> orderItems = em.createQuery(
            "SELECT oi FROM OrderItem oi JOIN oi.product p WHERE oi.order.id = :orderId",OrderItem.class)
            .setParameter("orderId", orderId)
            .getResultList();
            
            for(OrderItem oi: orderItems){
                OrderItemDTO orderItem = new OrderItemDTO();
                orderItem.setOrderId(oi.getId().getOrderId());
                orderItem.setProductId(oi.getId().getProductId());
                orderItem.setProductName(oi.getProduct().getName());
                orderItem.setPrice(oi.getProduct().getPrice());
                orderItem.setQuantity(oi.getQuantity());
                
                orderItemsDTO.add(orderItem);
            }
        } catch (Exception e) {
            new RuntimeException("Failed to retreive order items: " + e.getMessage());
        }
        return orderItemsDTO;
    }
}
