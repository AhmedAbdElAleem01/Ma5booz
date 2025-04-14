package com.bakefinity.controller.repositories.impls;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.bakefinity.controller.repositories.interfaces.OrderRepo;
import com.bakefinity.model.dtos.OrderDTO;
import com.bakefinity.model.dtos.OrderItemDTO;
import com.bakefinity.model.entities.Order;
import com.bakefinity.model.entities.User;
import com.bakefinity.model.enums.OrderStatus;
import com.bakefinity.utils.ConnectionManager;
import com.bakefinity.utils.EntityManagerFactorySingleton;
import com.oracle.wls.shaded.org.apache.xpath.operations.Or;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class OrderRepoImpl implements OrderRepo{
    @Override
    public int create(OrderDTO orderDTO) throws SQLException {
        if (orderDTO == null) {
            System.err.println("Error creating order: order is null");
            return -1;
        }
        EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
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
        EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
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
        EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
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
        EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
        try {
            List<Order> orders = new ArrayList<>();
            em.getTransaction().begin();
            User user = em.find(User.class, customerId);
            if(user == null){
                System.out.println("there is no user with id = " + customerId);
                return new ArrayList<>();
            }
            Query query = em.createQuery("select o from Order o where o.user=:customer", Order.class).setParameter("customer", user);
            orders = query.getResultList();
            if (orders.isEmpty()) {
                System.out.println("customer with id = " + customerId + " has no orders");
                return new ArrayList<>();
            }
            em.getTransaction().commit();
            return orders;
        }
        finally {
            em.close();
        }
    }

    @Override
    public List<OrderItemDTO> getOrderItemsByOrderId(int orderId) {
        EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
        try {
            List<OrderItemDTO> orderItems = null;
            em.getTransaction().begin();
            Query query = em.createQuery("select oi from OrderItem oi join oi.product p where oi.order.id = :orderId").setParameter("orderId", orderId);
            orderItems = query.getResultList();
            if (orderItems.isEmpty()) {
                return new ArrayList<>();
            }
            em.getTransaction().commit();
            return orderItems;
        }
        finally {
            em.close();
        }
    }
}
