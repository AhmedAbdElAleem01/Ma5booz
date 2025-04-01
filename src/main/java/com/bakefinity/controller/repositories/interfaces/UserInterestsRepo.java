package com.bakefinity.controller.repositories.interfaces;

import com.bakefinity.model.dtos.UserInterests;

import java.sql.SQLException;

public interface UserInterestsRepo {
    boolean createUserInterests(UserInterests userInterests) throws SQLException;
}
