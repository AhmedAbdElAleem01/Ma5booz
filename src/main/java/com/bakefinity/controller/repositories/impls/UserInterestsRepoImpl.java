package com.bakefinity.controller.repositories.impls;

import com.bakefinity.controller.repositories.interfaces.UserInterestsRepo;
import com.bakefinity.model.dtos.UserInterestsDTO;
import com.bakefinity.model.entities.Category;
import com.bakefinity.model.entities.User;
import com.bakefinity.utils.ConnectionManager;
import com.bakefinity.utils.EntityManagerFactorySingleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserInterestsRepoImpl implements UserInterestsRepo {
    @Override
    public boolean createUserInterests(UserInterestsDTO userInterests) throws SQLException {
        if (userInterests == null) {
            System.err.println("Error creating userInterests: UserInterests is null");
            return false;
        }
        EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
        try {
            em.getTransaction().begin();
            User user = em.find(User.class, userInterests.getUserId());
            if (user == null) {
                System.out.println("there is no user with id = " + userInterests.getUserId());
                return false;
            }
            Query query = em.createQuery("from Category c where c.id = :catId").setParameter("catId", userInterests.getCategoryId());
            List<Category> resultList = query.getResultList();
            if (resultList.isEmpty()) {
                System.out.println("No such categories");
                return false;
            }
            Set<Category> categories = new HashSet<>(resultList);
            user.getCategories().addAll(categories);
            em.getTransaction().commit();
            return true;
        }
        finally {
            em.close();
        }
    }

    @Override
    public List<UserInterestsDTO> getUserInterests(int userId) throws SQLException {
        EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
        try {
            em.getTransaction().begin();
            User user = em.find(User.class, userId);
            if (user == null) {
                System.out.println("there is no user with id = " + userId);
                return new ArrayList<>();
            }
            Query query = em.createQuery("select u.categories from User u where u.id = :uId").setParameter("uId", userId);
            List<Category> categories = query.getResultList();
            if (categories.isEmpty()) {
                return new ArrayList<>();
            }
            List<UserInterestsDTO> ret = new ArrayList<>();
            for (Category category : categories) {
                UserInterestsDTO dto = new UserInterestsDTO(userId, category.getId());
                ret.add(dto);
            }
            em.getTransaction().commit();
            return ret;
        }
        finally {
            em.close();
        }
    }
}