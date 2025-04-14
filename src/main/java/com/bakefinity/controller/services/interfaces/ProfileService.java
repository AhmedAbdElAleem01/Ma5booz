package com.bakefinity.controller.services.interfaces;

import java.util.Optional;
import com.bakefinity.model.dtos.*;

public interface ProfileService {
    Optional<UserDTO> getUserProfile(int userId);
    Optional<AddressDTO> getAddress(int userId);
    Optional<UserDTO> updateCreditLimit(UserDTO user , Double newCreditLimit);
    Optional<UserDTO> updateShippingInfo(UserDTO user , String country , String city , String street ,String BNo ,String mobile);
    Optional<UserDTO> updateAccountDetails(UserDTO user , String username , String job , String email);
    Optional<UserDTO> updatePassword(UserDTO user, String currentPass, String newPass, String confirmPass);
    boolean isUsernameTaken(String username);
}
