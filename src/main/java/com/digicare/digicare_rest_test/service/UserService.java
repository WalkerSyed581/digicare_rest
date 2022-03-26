package com.digicare.digicare_rest_test.service;

import com.digicare.digicare_rest_test.model.user.User;
import com.digicare.digicare_rest_test.payload.ApiResponse;
import com.digicare.digicare_rest_test.payload.SignUpRequest;
import com.digicare.digicare_rest_test.payload.UserIdentityAvailability;
import com.digicare.digicare_rest_test.security.UserPrincipal;

public interface UserService {


    UserIdentityAvailability checkUsernameAvailability(String username);

    UserIdentityAvailability checkEmailAvailability(String email);

    User addUser(SignUpRequest user);

    User updateUser(User newUser, Long id, UserPrincipal currentUser);

    ApiResponse deleteUser(Long id,UserPrincipal currentUser);

//    UserProfile setOrUpdateInfo(UserPrincipal currentUser, InfoRequest infoRequest);
}
