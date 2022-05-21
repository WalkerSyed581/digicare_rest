package com.digicare.digicare_rest_test.service;

import com.digicare.digicare_rest_test.model.user.*;
import com.digicare.digicare_rest_test.model.user.PatientDoctor;
import com.digicare.digicare_rest_test.payload.ApiResponse;
import com.digicare.digicare_rest_test.payload.AssessmentRequest;
import com.digicare.digicare_rest_test.payload.NotificationRequest;
import com.digicare.digicare_rest_test.security.UserPrincipal;

public interface NotificationService {
	// UserIdentityAvailability checkEmailAvailability(String email);

	// User getUserProfile(String username);

	Notification addNotification(NotificationRequest newNotification);

	// Assessment updateAssessment(AssessmentRequest newAssessment,Long id, UserPrincipal currentUser);

   	ApiResponse deleteNotification(Long id,UserPrincipal currentUser);

	// UserProfile setOrUpdateInfo(UserPrincipal currentUser, InfoRequest infoRequest);
}
