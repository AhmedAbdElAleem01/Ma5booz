package com.bakefinity.controller.services.impls;
import java.util.Optional;

import com.bakefinity.controller.repositories.impls.*;
import com.bakefinity.controller.repositories.interfaces.AddressRepo;
import com.bakefinity.controller.repositories.interfaces.ProfileRepo;
import com.bakefinity.controller.repositories.interfaces.UserRepo;
import com.bakefinity.controller.services.interfaces.ProfileService;
import com.bakefinity.model.dtos.*;

public class ProfileServiceImpl implements ProfileService {
    ProfileRepo profileRepo;
    AddressRepo addressRepo;
    UserRepo userRepo;
    private static ProfileServiceImpl instance;

    private ProfileServiceImpl(){
        profileRepo = new ProfileRepoImpl();
        addressRepo = new AddressRepoImpl();
        userRepo = new UserRepoImpl();
    }
    public static ProfileService getInstance() {
        if (instance == null) {
            synchronized (ProfileServiceImpl.class) {
                if (instance == null) {
                    instance = new ProfileServiceImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public Optional<UserDTO> getUserProfile(int userId) {
        if(userId<-1)
            return Optional.empty();
        Optional<UserDTO> user = userRepo.findById(userId);
        if(user.isEmpty())
            return Optional.empty();
        return user;
    }
    @Override
    public Optional<AddressDTO> getAddress(int userId) {
        return addressRepo.findUserAddressById(userId);
    }

    @Override
    public Optional<UserDTO> updateCreditLimit(UserDTO user , Double newCreditLimit) {
        // validation
        Optional<UserDTO> retrievedUser = userRepo.findById(user.getId());
        if(retrievedUser.isEmpty()){
            return Optional.empty();
        }
        if(newCreditLimit<=0)
            return Optional.empty();
        Optional<UserDTO> updatedUser = profileRepo.updateCreditLimit(user , newCreditLimit);
        if(updatedUser.isPresent()){
            return updatedUser;
        }
        return Optional.empty();
    }

    @Override
    public Optional<UserDTO> updateShippingInfo(UserDTO user, String country, String city, String street, 
    String BNo, String mobile) {

        // validation
        Optional<UserDTO> retrievedUser = userRepo.findById(user.getId());
        Optional <AddressDTO> retrievedAddress = addressRepo.findUserAddressById(user.getId());

        if(country.isBlank() || city.isBlank() || street.isBlank() || BNo.isBlank()
            || mobile.isBlank() || retrievedUser.isEmpty() || retrievedAddress.isEmpty()){
            return Optional.empty();
        }

        Optional<AddressDTO> updatedAddress = profileRepo.updateShippingAddress(retrievedUser.get() , country , city , street , BNo);
        Optional<UserDTO> updatedUser = profileRepo.updatePhoneNumber(retrievedUser.get() , mobile);
        if(updatedAddress.isEmpty() || updatedUser.isEmpty()){
            return Optional.empty();
        }
        return updatedUser;
    }

    @Override
    public Optional<UserDTO> updateAccountDetails(UserDTO user, String username, String job, String email) {
        // validation
        Optional<UserDTO> retrievedUser = userRepo.findById(user.getId());
        if(username.isBlank() || job.isBlank() || email.isBlank() || retrievedUser.isEmpty()){
            return Optional.empty();
        }

        Optional<UserDTO> updatedUser = profileRepo.updateAccountDetails(retrievedUser.get() , username , job , email);
        if(updatedUser.isPresent()){
            return updatedUser;
        }
        return Optional.empty();
    }

    @Override
    public Optional<UserDTO> updatePassword(UserDTO user, String currentPass, String newPass, String confirmPass) {
        // validation
        if(currentPass.isBlank() || newPass.isBlank() || confirmPass.isBlank())
            return Optional.empty();

        if(!newPass.equals(confirmPass))
            return Optional.empty();
        
        Optional<UserDTO> retrievedUser = userRepo.findById(user.getId());
        if (retrievedUser.isEmpty()|| !retrievedUser.get().getPassword().equals(currentPass)) {
            return Optional.empty();
        }

        Optional<UserDTO> updatedUser = profileRepo.updatePassword(retrievedUser.get() , newPass);
        if(updatedUser.isPresent()){
            return updatedUser;
        }
        return Optional.empty();
    }

    @Override
    public boolean isUsernameTaken(String username) {
        if(username.isBlank())
            return false;
        return profileRepo.isUsernameTaken(username);
    }
    
}
