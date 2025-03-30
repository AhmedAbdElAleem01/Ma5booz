package com.bakefinity.controller.repositories.interfaces;

import java.util.Optional;

import com.bakefinity.model.entities.User;

public interface AdminRepo {
    public Optional<User> findByEmailAndPassword(String email ,String password);
}

