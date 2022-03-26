package com.digicare.digicare_rest_test.service.impl;

import com.digicare.digicare_rest_test.model.user.*;
import com.digicare.digicare_rest_test.model.role.*;
import com.digicare.digicare_rest_test.security.*;
import com.digicare.digicare_rest_test.payload.ApiResponse;
import com.digicare.digicare_rest_test.payload.PermissionRequest;
import com.digicare.digicare_rest_test.payload.ReadingRequest;
import com.digicare.digicare_rest_test.exception.*;
import com.digicare.digicare_rest_test.repository.PatientDoctorRepository;
import com.digicare.digicare_rest_test.repository.SensorPatientRepository;
import com.digicare.digicare_rest_test.repository.SensorRepository;
import com.digicare.digicare_rest_test.repository.UserRepository;
import com.digicare.digicare_rest_test.service.PatientDoctorService;
import com.digicare.digicare_rest_test.service.SensorPatientDataService;

import org.hibernate.secure.spi.PermissionCheckEntityInformation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


@Service
public class PatientDoctorServiceImpl implements PatientDoctorService {
    @Autowired
    private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;


	@Autowired
	private PatientDoctorRepository permissionRepository;

	@Override
	public PatientDoctor addPermission(PermissionRequest newPermission) {
		PatientDoctorKey permissionKey = new PatientDoctorKey();

		modelMapper.map(newPermission,permissionKey);
		
		
		
        if (permissionRepository.existsById(permissionKey)) {
            ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, "Permission already given");
            throw new BadRequestException(apiResponse);
        }

		if(!userRepository.existsById(permissionKey.getDoctorId()) ||
		!userRepository.existsById(permissionKey.getPatientId())){
			ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, "No users exists for given IDs");
            throw new BadRequestException(apiResponse);
		} 



		PatientDoctor permission = new PatientDoctor();

		permission.setPatient(userRepository.findById(permissionKey.getPatientId()).orElseThrow(() -> new UserNotFoundException(permissionKey.getPatientId())));
		permission.setDoctor(userRepository.findById(permissionKey.getDoctorId()).orElseThrow(() -> new UserNotFoundException(permissionKey.getDoctorId())));



		return permissionRepository.save(permission);
	}

	@Override
	public ApiResponse deletePermission(Long patient_id, Long doctor_id,UserPrincipal currentUser) {
		PatientDoctorKey permissionKey = new PatientDoctorKey(patient_id,doctor_id);
		
		PatientDoctor permission = permissionRepository.findById(permissionKey)
						.orElseThrow(() -> new PermissionNotFoundException(patient_id, doctor_id));

		if (!currentUser.getAuthorities()
				.contains(new SimpleGrantedAuthority(RoleName.ROLE_ADMIN.toString()))) {
			ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, "You don't have permission to delete permission of: " + permission);
			throw new AccessDeniedException(apiResponse);
		}
		permissionRepository.deleteById(permissionKey);
		
		return new ApiResponse(Boolean.TRUE, "You successfully deleted permission: " + permissionKey);

	}

	@Override
	public PatientDoctor updatePermission(PermissionRequest newPermission) {
		// PatientDoctor reading = sensorPatientRepository.getById();
        // if (currentUser.getAuthorities().contains(new SimpleGrantedAuthority(RoleName.ROLE_ADMIN.toString()))) {
        //     reading.setReading(updatedReading.getReading());
        //     reading.setTimestamp(updatedReading.getTimestamp());

        //     return sensorPatientRepository.save(reading);

        // }

        // ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, "You don't have permission to update reading of: " + id);
        // throw new AccessDeniedException(apiResponse);

		return null;
	}

    
}
