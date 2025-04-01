package com.bakefinity.controller.repositories.interfaces;

import com.bakefinity.model.dtos.Address;
import com.bakefinity.model.dtos.User;

import java.sql.SQLException;

public interface AddressRepo {
    boolean createAddress(Address address) throws SQLException;
}
