package com.digicare.digicare_rest_test.service;

import com.digicare.digicare_rest_test.model.user.PatientDoctor;
import com.digicare.digicare_rest_test.payload.ApiResponse;
import com.digicare.digicare_rest_test.payload.PermissionRequest;
import com.digicare.digicare_rest_test.security.UserPrincipal;

public interface PatientDoctorService {
	 //    UserIdentityAvailability checkEmailAvailability(String email);

//    User getUserProfile(String username);

	PatientDoctor addPermission(PermissionRequest permission);

	PatientDoctor updatePermission(PermissionRequest newPermission);

   ApiResponse deletePermission(Long patient_id,Long doctor_id,UserPrincipal currentUser);

//    UserProfile setOrUpdateInfo(UserPrincipal currentUser, InfoRequest infoRequest);
}
