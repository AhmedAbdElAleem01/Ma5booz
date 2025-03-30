package com.bakefinity.controller.repositories.interfaces;

import java.util.Optional;

import com.bakefinity.model.entities.Address;
import com.bakefinity.model.entities.User;

public interface ProfileRepo {    
    public Optional<Address> findUserAddressById(int id);
    public Optional<User> updateCreditLimit(User user , String creditLimit);
    public Optional<Address> updateShippingAddress(User user, String country, String city, String street, String bNo);
    public Optional<User> updateAccountDetails(User user, String username, String job, String email);
    public Optional<User> updatePassword(User user, String newPass);
    public boolean isUsernameTaken(String username);
    public Optional<User> updatePhoneNumber(User user, String mobile);
}
