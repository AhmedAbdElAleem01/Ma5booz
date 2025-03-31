package com.bakefinity.controller.repositories.interfaces;

import com.bakefinity.model.dtos.User;

import java.sql.SQLException;

public interface UserRepo {
    int createUser(User user) throws SQLException;
    boolean isFoundUsername(String username) throws SQLException;
    boolean isFoundEmail(String email) throws SQLException;
}
