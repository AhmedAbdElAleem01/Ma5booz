package com.bakefinity.controller.services.impls;

import com.bakefinity.controller.repositories.impls.UserRepoImpl;
import com.bakefinity.controller.repositories.interfaces.UserRepo;
import com.bakefinity.controller.services.interfaces.UserRegisterService;
import com.bakefinity.model.dtos.UserDTO;
import java.sql.SQLException;

public class UserRegisterServiceImpl implements UserRegisterService {
    UserRepo userRepo = new UserRepoImpl();
    private static UserRegisterServiceImpl instance;

    public static synchronized UserRegisterServiceImpl getInstance() {
        if (instance == null) {
            instance = new UserRegisterServiceImpl();
        }
        return instance;
    }

    @Override
    public int createUser(UserDTO user) throws SQLException{
        return userRepo.createUser(user);
    }

    @Override
    public boolean isUsernameAvailable(String username) throws SQLException {
        return !userRepo.isFoundUsername(username);
    }

    @Override
    public boolean isEmailUnique(String email) throws SQLException{
        return !userRepo.isFoundEmail(email);
    }
}