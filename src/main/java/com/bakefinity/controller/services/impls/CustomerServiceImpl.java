package com.bakefinity.controller.services.impls;

import java.sql.SQLException;
import java.util.List;

import com.bakefinity.controller.repositories.impls.UserRepoImpl;
import com.bakefinity.controller.repositories.interfaces.UserRepo;
import com.bakefinity.controller.services.interfaces.CustomerService;
import com.bakefinity.model.entities.User;

public class CustomerServiceImpl implements CustomerService{
 private static CustomerServiceImpl instance;
    private final UserRepo userRepo;

    private CustomerServiceImpl() {
        this.userRepo = new UserRepoImpl();
    }

    public static CustomerService getInstance() {
        if (instance == null) {
            synchronized (CustomerServiceImpl.class) {
                if (instance == null) {
                    instance = new CustomerServiceImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public List<User> getAllCustomers() {
        try {
            return userRepo.getAllUsers();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
