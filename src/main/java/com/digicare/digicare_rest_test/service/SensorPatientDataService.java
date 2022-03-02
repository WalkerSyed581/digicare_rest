package com.digicare.digicare_rest_test.service;

import com.digicare.digicare_rest_test.model.SensorPatientData;

public interface SensorPatientDataService {
    //    UserIdentityAvailability checkEmailAvailability(String email);

//    User getUserProfile(String username);

    SensorPatientData addReading(SensorPatientData reading);

    SensorPatientData updateReading(SensorPatientData newReading);

//    Delte deleteUser(String username);

//    UserProfile setOrUpdateInfo(UserPrincipal currentUser, InfoRequest infoRequest);
}
