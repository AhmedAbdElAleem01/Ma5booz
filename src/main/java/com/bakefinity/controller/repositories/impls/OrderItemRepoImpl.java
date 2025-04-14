package com.bakefinity.controller.repositories.impls;

import com.bakefinity.controller.repositories.interfaces.OrderItemRepo;
import com.bakefinity.model.dtos.UserInterestsDTO;
import com.bakefinity.model.entities.OrderItem;
import com.bakefinity.utils.ConnectionManager;
import com.bakefinity.utils.EntityManagerFactorySingleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderItemRepoImpl implements OrderItemRepo {
    @Override
    public boolean create(OrderItem orderItem) throws SQLException {
        if (orderItem == null) {
            System.err.println("Error creating orderItem: OrderItem is null");
            return false;
        }
        EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(orderItem);
            em.getTransaction().commit();
            return true;
        }
        finally {
            em.close();
        }
    }
}
