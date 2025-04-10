package com.bakefinity.controller.services.interfaces;

import com.bakefinity.model.dtos.UserInterestsDTO;
import java.sql.SQLException;
import java.util.List;

public interface UserInterestsService {
    boolean createUserInterests(UserInterestsDTO userInterests) throws SQLException;
    List<UserInterestsDTO> getUserInterests(int userId);
}
