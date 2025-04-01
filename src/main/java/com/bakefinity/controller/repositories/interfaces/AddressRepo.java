package com.bakefinity.controller.repositories.interfaces;

import com.bakefinity.model.dtos.AddressDTO;

import java.sql.SQLException;

public interface AddressRepo {
    boolean createAddress(AddressDTO address) throws SQLException;
}
