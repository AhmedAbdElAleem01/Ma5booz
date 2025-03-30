package com.bakefinity.controller.services.impls;

import java.util.Optional;
import com.bakefinity.controller.repositories.impls.AdminRepoImpl;
import com.bakefinity.controller.repositories.impls.UserRepoImpl;
import com.bakefinity.controller.repositories.interfaces.*;
import com.bakefinity.controller.services.interfaces.*;
import com.bakefinity.model.entities.User;

public class UserLoginServiceImpl implements UserLoginService {
    UserRepo userRepo = new UserRepoImpl();

    @Override
    public Optional<User> login(String email, String password) {
        if (email.isBlank() || password.isBlank()) 
            return Optional.empty();
        
        Optional<User> user = userRepo.findByEmailAndPassword(email, password);
        
        if (user.isPresent()) {
            user.get().setRole("USER");
            return user;
        }
        AdminRepo adminRepo = new AdminRepoImpl();
        Optional<User> admin = adminRepo.findByEmailAndPassword(email, password);
        if (admin.isPresent()) {
            admin.get().setRole("ADMIN");
            return admin;
        }
        return Optional.empty();
    }
    
}
