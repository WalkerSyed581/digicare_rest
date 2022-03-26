package com.digicare.digicare_rest_test.service;

import com.digicare.digicare_rest_test.model.Assessment;
import com.digicare.digicare_rest_test.model.user.PatientDoctor;
import com.digicare.digicare_rest_test.payload.ApiResponse;
import com.digicare.digicare_rest_test.payload.AssessmentRequest;
import com.digicare.digicare_rest_test.security.UserPrincipal;

public interface AssessmentService {
	// UserIdentityAvailability checkEmailAvailability(String email);

	// User getUserProfile(String username);

	Assessment addAssessment(AssessmentRequest newAssessment,UserPrincipal currentUser);

	Assessment updateAssessment(AssessmentRequest newAssessment,Long id, UserPrincipal currentUser);

   	ApiResponse deleteAssessment(Long id,UserPrincipal currentUser);

	// UserProfile setOrUpdateInfo(UserPrincipal currentUser, InfoRequest infoRequest);
}
