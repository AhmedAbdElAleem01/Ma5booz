package com.bakefinity.controller.services.interfaces;

import java.util.Optional;
import com.bakefinity.model.dtos.*;

public interface ProfileService {
    public Optional<AddressDTO> getAddress(int userId);
    public Optional<UserDTO> updateCreditLimit(UserDTO user , Double newCreditLimit);
    public Optional<UserDTO> updateShippingInfo(UserDTO user , String country , String city , String street ,String BNo ,String mobile);
    public Optional<UserDTO> updateAccountDetails(UserDTO user , String username , String job , String email);
    public Optional<UserDTO> updatePassword(UserDTO user, String currentPass, String newPass, String confirmPass);
    public boolean isUsernameTaken(String username);
}
