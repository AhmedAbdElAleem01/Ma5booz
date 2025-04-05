package com.bakefinity.controller.services.interfaces;

import com.bakefinity.model.dtos.UserInterestsDTO;
import java.sql.SQLException;

public interface UserInterestsService {
    boolean createUserInterests(UserInterestsDTO userInterests) throws SQLException;
}
