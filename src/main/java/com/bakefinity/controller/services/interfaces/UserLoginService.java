package com.bakefinity.controller.services.interfaces;

import java.util.Optional;

import com.bakefinity.model.dtos.UserDTO;

public interface UserLoginService {
    public Optional<UserDTO> login(String email , String password);
}
