package com.bakefinity.controller.services.interfaces;

import java.util.Optional;

import com.bakefinity.model.dtos.User;

public interface UserLoginService {
    public Optional<User> login(String email , String password);
}
