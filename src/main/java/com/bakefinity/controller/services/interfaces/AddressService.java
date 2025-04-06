package com.bakefinity.controller.services.interfaces;

import com.bakefinity.model.dtos.AddressDTO;

import java.sql.SQLException;

public interface AddressService {
    boolean createAddress(AddressDTO address) throws SQLException;
}