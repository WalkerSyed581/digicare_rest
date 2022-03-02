package com.digicare.digicare_rest_test.service;

import com.digicare.digicare_rest_test.model.Sensor;


public interface SensorService {

//    UserIdentityAvailability checkEmailAvailability(String email);

//    User getUserProfile(String username);

    Sensor addSensor(Sensor sensor);

    Sensor updateSensor(Sensor newSensor);

//    Delte deleteUser(String username);

//    UserProfile setOrUpdateInfo(UserPrincipal currentUser, InfoRequest infoRequest);
}
