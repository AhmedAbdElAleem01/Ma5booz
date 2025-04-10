package com.bakefinity.controller.services.impls;

import com.bakefinity.controller.repositories.impls.UserInterestsRepoImpl;
import com.bakefinity.controller.repositories.interfaces.UserInterestsRepo;
import com.bakefinity.controller.services.interfaces.UserInterestsService;
import com.bakefinity.model.dtos.UserInterestsDTO;

import java.sql.SQLException;
import java.util.List;

public class UserInterestsServiceImpl implements UserInterestsService {
    UserInterestsRepo userInterestsRepo = new UserInterestsRepoImpl();
    private static UserInterestsServiceImpl instance;

    public static synchronized UserInterestsServiceImpl getInstance() {
        if (instance == null) {
            instance = new UserInterestsServiceImpl();
        }
        return instance;
    }

    @Override
    public boolean createUserInterests(UserInterestsDTO userInterests) throws SQLException {
        return userInterestsRepo.createUserInterests(userInterests);
    }
    
    @Override
    public List<UserInterestsDTO> getUserInterests(int userId){
        try {
            return userInterestsRepo.getUserInterests(userId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}