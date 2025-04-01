package com.bakefinity.controller.repositories.interfaces;

import java.sql.SQLException;
import java.util.Optional;
import com.bakefinity.model.dtos.User;

public interface UserRepo {
    public Optional<User> findByEmailAndPassword(String email ,String password);
    public Optional<User> findById(int id);
    int createUser(User user) throws SQLException;
    boolean isFoundUsername(String username) throws SQLException;
    boolean isFoundEmail(String email) throws SQLException;
}
