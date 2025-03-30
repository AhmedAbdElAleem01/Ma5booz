package com.bakefinity.controller.repositories.interfaces;

import java.util.Optional;
import com.bakefinity.model.entities.User;

public interface UserRepo {
    public Optional<User> findByEmailAndPassword(String email ,String password);
    public Optional<User> findById(int id);
}
