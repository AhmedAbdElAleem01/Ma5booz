package com.bakefinity.controller.services.impls;
import java.util.Optional;

import com.bakefinity.controller.repositories.impls.ProfileRepoImpl;
import com.bakefinity.controller.repositories.impls.UserRepoImpl;
import com.bakefinity.controller.repositories.interfaces.ProfileRepo;
import com.bakefinity.controller.repositories.interfaces.UserRepo;
import com.bakefinity.controller.services.interfaces.ProfileService;
import com.bakefinity.model.entities.Address;
import com.bakefinity.model.entities.User;

public class ProfileServiceImpl implements ProfileService {
    ProfileRepo profileRepo = new ProfileRepoImpl();
    UserRepo userRepo = new UserRepoImpl();

    @Override
    public Optional<Address> getAddress(int userId) {
        if(userId<-1)
            return Optional.empty();

        Optional<Address> address = profileRepo.findUserAddressById(userId);
        
        if (address.isPresent()) {
            return address;
        }else{
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> updateCreditLimit(User user , String newCreditLimit) {
        // validation
        Optional<User> retrievedUser = userRepo.findById(user.getId());
        if(newCreditLimit.isBlank() || newCreditLimit.isEmpty() || retrievedUser.isEmpty()){
            return Optional.empty();
        }
        if(Integer.parseInt(newCreditLimit)<=0)
            return Optional.empty();
        Optional<User> updatedUser = profileRepo.updateCreditLimit(retrievedUser.get() , newCreditLimit);
        if(updatedUser.isPresent()){
            return updatedUser;
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> updateShippingInfo(User user, String country, String city, String street, 
    String BNo, String mobile) {

        // validation
        Optional<User> retrievedUser = userRepo.findById(user.getId());
        Optional <Address> retrievedAddress = profileRepo.findUserAddressById(user.getId());

        if(country.isBlank() || city.isBlank() || street.isBlank() || BNo.isBlank()
            || mobile.isBlank() || retrievedUser.isEmpty() || retrievedAddress.isEmpty()){
            return Optional.empty();
        }

        Optional<Address> updatedAddress = profileRepo.updateShippingAddress(retrievedUser.get() , country , city , street , BNo);
        Optional<User> updatedUser = profileRepo.updatePhoneNumber(retrievedUser.get() , mobile);
        if(updatedAddress.isEmpty() || updatedUser.isEmpty()){
            return Optional.empty();
        }
        return updatedUser;
    }

    @Override
    public Optional<User> updateAccountDetails(User user, String username, String job, String email) {
        // validation
        Optional<User> retrievedUser = userRepo.findById(user.getId());
        if(username.isBlank() || job.isBlank() || email.isBlank() || retrievedUser.isEmpty()){
            return Optional.empty();
        }

        Optional<User> updatedUser = profileRepo.updateAccountDetails(retrievedUser.get() , username , job , email);
        if(updatedUser.isPresent()){
            return updatedUser;
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> updatePassword(User user, String currentPass, String newPass, String confirmPass) {
        // validation
        if(currentPass.isBlank() || newPass.isBlank() || confirmPass.isBlank())
            return Optional.empty();

        if(!newPass.equals(confirmPass))
            return Optional.empty();
        
        Optional<User> retrievedUser = userRepo.findById(user.getId());
        if (retrievedUser.isEmpty()|| !retrievedUser.get().getPassword().equals(currentPass)) {
            return Optional.empty();
        }

        Optional<User> updatedUser = profileRepo.updatePassword(retrievedUser.get() , newPass);
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
