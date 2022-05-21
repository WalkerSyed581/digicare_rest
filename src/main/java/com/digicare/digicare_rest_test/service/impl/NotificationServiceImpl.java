package com.digicare.digicare_rest_test.service.impl;

import com.digicare.digicare_rest_test.exception.BadRequestException;
import com.digicare.digicare_rest_test.exception.UserNotFoundException;
import com.digicare.digicare_rest_test.model.user.Notification;
import com.digicare.digicare_rest_test.model.user.User;
import com.digicare.digicare_rest_test.payload.ApiResponse;
import com.digicare.digicare_rest_test.payload.NotificationRequest;
import com.digicare.digicare_rest_test.repository.NotificationRepository;
import com.digicare.digicare_rest_test.repository.UserRepository;
import com.digicare.digicare_rest_test.security.UserPrincipal;
import com.digicare.digicare_rest_test.service.NotificationService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService{
	@Autowired
    private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
    private NotificationRepository notificationRepository;


	@Override
	public Notification addNotification(NotificationRequest newNotification) {
		

		Notification notification = new Notification();
		
		User patient = userRepository.findById(newNotification.getPatient_id()).orElseThrow(() -> new UserNotFoundException(newNotification.getPatient_id()));

		notification.setContent(newNotification.getContent());
		notification.setPatient(patient);
		notification.setTimestamp(newNotification.getTimestamp());
		notification.setTitle(newNotification.getTitle());

		return notificationRepository.save(notification);
	}

	@Override
	public ApiResponse deleteNotification(Long id, UserPrincipal currentUser) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
