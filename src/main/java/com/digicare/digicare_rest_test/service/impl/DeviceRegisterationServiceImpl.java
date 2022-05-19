package com.digicare.digicare_rest_test.service.impl;

import com.digicare.digicare_rest_test.model.user.*;
import com.digicare.digicare_rest_test.model.SensorPatientData;
import com.digicare.digicare_rest_test.model.role.*;
import com.digicare.digicare_rest_test.security.*;
import com.digicare.digicare_rest_test.payload.ApiResponse;
import com.digicare.digicare_rest_test.payload.DeviceRegisterationRequest;
import com.digicare.digicare_rest_test.payload.ReadingRequest;
import com.digicare.digicare_rest_test.exception.*;
import com.digicare.digicare_rest_test.repository.DeviceRegisterationRepository;
import com.digicare.digicare_rest_test.repository.SensorPatientRepository;
import com.digicare.digicare_rest_test.repository.SensorRepository;
import com.digicare.digicare_rest_test.repository.UserRepository;
import com.digicare.digicare_rest_test.service.DeviceRegisterationService;
import com.digicare.digicare_rest_test.service.SensorPatientDataService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Service
public class DeviceRegisterationServiceImpl implements DeviceRegisterationService {
	@Autowired
    private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;


    @Autowired
	private SensorPatientRepository sensorPatientRepository;
	
	@Autowired
	private SensorRepository sensorRepository;

	@Autowired
	private DeviceRegisterationRepository deviceRepository;



	@Override
	public DeviceRegisteration addDevice(DeviceRegisterationRequest deviceRegistrationRequest,
			UserPrincipal currentUser) {
		// TODO Auto-generated method stub

		if (deviceRepository.existsByPatient_Id(deviceRegistrationRequest.getPatient_id())) {
            ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, "Patient already registered");
            throw new BadRequestException(apiResponse);
        }

		DeviceRegisteration newDevice = new DeviceRegisteration();

		newDevice.setCreated_at(deviceRegistrationRequest.getCreated_at());
		newDevice.setPatient(userRepository.findById(deviceRegistrationRequest.getPatient_id()).orElseThrow(() -> new UserNotFoundException(deviceRegistrationRequest.getPatient_id())));
		newDevice.setPublic_key(deviceRegistrationRequest.getPublic_key());

		return deviceRepository.save(newDevice);
	}

	@Override
	public DeviceRegisteration updateDevice(DeviceRegisterationRequest updateDeviceRegisterationRequest, Long id,
			UserPrincipal currentUser) {
		// TODO Auto-generated method stub
		return null;
	}

    @Override
    public ApiResponse deleteDevice(Long id,UserPrincipal currentUser) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        if (!user.getId().equals(currentUser.getId()) || !currentUser.getAuthorities()
				.contains(new SimpleGrantedAuthority(RoleName.ROLE_ADMIN.toString()))) {
			ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, "You don't have permission to delete profile of: " + id);
			throw new AccessDeniedException(apiResponse);
		}

        sensorPatientRepository.deleteById(id);

        return new ApiResponse(Boolean.TRUE, "You successfully deleted reading: " + id);
    }

	
}
