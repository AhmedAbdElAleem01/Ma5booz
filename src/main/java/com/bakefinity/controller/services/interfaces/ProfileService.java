package com.bakefinity.controller.services.interfaces;

import java.util.Optional;
import com.bakefinity.model.entities.Address;
import com.bakefinity.model.entities.User;

public interface ProfileService {
    public Optional<Address> getAddress(int userId);
    public Optional<User> updateCreditLimit(User user , String newCreditLimit);
    public Optional<User> updateShippingInfo(User user , String country , String city , String street ,String BNo ,String mobile);
    public Optional<User> updateAccountDetails(User user , String username , String job , String email);
    public Optional<User> updatePassword(User user, String currentPass, String newPass, String confirmPass);
    public boolean isUsernameTaken(String username);
}
