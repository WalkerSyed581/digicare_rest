package com.digicare.digicare_rest_test.service;

import com.digicare.digicare_rest_test.model.user.User;
import com.digicare.digicare_rest_test.payload.ApiResponse;
import com.digicare.digicare_rest_test.payload.UserIdentityAvailability;
import com.digicare.digicare_rest_test.security.UserPrincipal;

public interface UserService {


    UserIdentityAvailability checkUsernameAvailability(String username);

    UserIdentityAvailability checkEmailAvailability(String email);

    User addUser(User user,int role);

    User updateUser(User newUser, Long id, UserPrincipal currentUser);

    ApiResponse deleteUser(Long id,UserPrincipal currentUser);

//    UserProfile setOrUpdateInfo(UserPrincipal currentUser, InfoRequest infoRequest);
}
