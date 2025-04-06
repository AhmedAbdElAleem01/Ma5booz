package com.bakefinity.controller.services.impls;

import com.bakefinity.controller.repositories.impls.AddressRepoImpl;
import com.bakefinity.controller.repositories.interfaces.AddressRepo;
import com.bakefinity.controller.services.interfaces.AddressService;
import com.bakefinity.model.dtos.AddressDTO;

import java.sql.SQLException;

public class AddressServiceImpl implements AddressService {
    AddressRepo addressRepo = new AddressRepoImpl();
    private static AddressServiceImpl instance;

    public static synchronized AddressServiceImpl getInstance() {
        if (instance == null) {
            instance = new AddressServiceImpl();
        }
        return instance;
    }

    @Override
    public boolean createAddress(AddressDTO address) throws SQLException {
        return addressRepo.createAddress(address);
    }
}