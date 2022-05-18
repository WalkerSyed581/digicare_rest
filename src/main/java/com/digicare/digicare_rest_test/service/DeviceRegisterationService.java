package com.digicare.digicare_rest_test.service;

import com.digicare.digicare_rest_test.model.SensorPatientData;
import com.digicare.digicare_rest_test.model.user.DeviceRegisteration;
import com.digicare.digicare_rest_test.payload.ApiResponse;
import com.digicare.digicare_rest_test.payload.DeviceRegisterationRequest;
import com.digicare.digicare_rest_test.payload.ReadingRequest;
import com.digicare.digicare_rest_test.security.UserPrincipal;

public interface DeviceRegisterationService {
    

    DeviceRegisteration addDevice(DeviceRegisterationRequest deviceRegistrationRequest, UserPrincipal currentUser);

    DeviceRegisteration updateDevice(DeviceRegisterationRequest updateDeviceRegisterationRequest, Long id, UserPrincipal currentUser);

    ApiResponse deleteDevice(Long id,UserPrincipal currentUser);

}
