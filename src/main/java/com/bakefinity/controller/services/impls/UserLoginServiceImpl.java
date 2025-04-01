package com.bakefinity.controller.services.impls;

import java.util.Optional;
import com.bakefinity.controller.repositories.impls.AdminRepoImpl;
import com.bakefinity.controller.repositories.impls.UserRepoImpl;
import com.bakefinity.controller.repositories.interfaces.*;
import com.bakefinity.controller.services.interfaces.*;
import com.bakefinity.model.dtos.UserDTO;

public class UserLoginServiceImpl implements UserLoginService {
    UserRepo userRepo = new UserRepoImpl();

    @Override
    public Optional<UserDTO> login(String email, String password) {
        if (email.isBlank() || password.isBlank()) 
            return Optional.empty();
        
        Optional<UserDTO> user = userRepo.findByEmailAndPassword(email, password);
        
        if (user.isPresent()) {
            user.get().setRole("USER");
            return user;
        }
        AdminRepo adminRepo = new AdminRepoImpl();
        Optional<UserDTO> admin = adminRepo.findByEmailAndPassword(email, password);
        if (admin.isPresent()) {
            admin.get().setRole("ADMIN");
            return admin;
        }
        return Optional.empty();
    }
    
}
