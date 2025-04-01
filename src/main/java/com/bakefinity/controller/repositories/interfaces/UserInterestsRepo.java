package com.bakefinity.controller.repositories.interfaces;

import com.bakefinity.model.dtos.UserInterestsDTO;

import java.sql.SQLException;

public interface UserInterestsRepo {
    boolean createUserInterests(UserInterestsDTO userInterests) throws SQLException;
}
