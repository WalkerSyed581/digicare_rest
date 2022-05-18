package com.digicare.digicare_rest_test.service;

import com.digicare.digicare_rest_test.model.SensorPatientData;
import com.digicare.digicare_rest_test.payload.ApiResponse;
import com.digicare.digicare_rest_test.payload.ReadingRequest;
import com.digicare.digicare_rest_test.security.UserPrincipal;

public interface SensorPatientDataService {
    //    UserIdentityAvailability checkEmailAvailability(String email);

//    User getUserProfile(String username);

    ApiResponse verifyAndAddReading(String newReading);

    SensorPatientData addReading(ReadingRequest readingRequest, UserPrincipal currentUser);

    SensorPatientData updateReading(ReadingRequest updatedReading, Long id, UserPrincipal currentUser);

    ApiResponse deleteReading(Long id,UserPrincipal currentUser);

//    UserProfile setOrUpdateInfo(UserPrincipal currentUser, InfoRequest infoRequest);
}
