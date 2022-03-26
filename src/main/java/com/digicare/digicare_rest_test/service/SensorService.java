package com.digicare.digicare_rest_test.service;

import com.digicare.digicare_rest_test.model.Sensor;
import com.digicare.digicare_rest_test.payload.ApiResponse;
import com.digicare.digicare_rest_test.payload.SensorRequest;
import com.digicare.digicare_rest_test.security.UserPrincipal;


public interface SensorService {

//    UserIdentityAvailability checkEmailAvailability(String email);

//    User getUserProfile(String username);

    Sensor addSensor(SensorRequest newSensor);

    Sensor updateSensor(SensorRequest newSensor,Long id);

    ApiResponse deleteSensor(Long id,UserPrincipal currentUser);

//    UserProfile setOrUpdateInfo(UserPrincipal currentUser, InfoRequest infoRequest);
}
