package com.digicare.digicare_rest_test.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.digicare.digicare_rest_test.model.user.DeviceRegisteration;
import com.digicare.digicare_rest_test.model.user.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeviceRegisterationRepository extends JpaRepository<DeviceRegisteration, Long> {
	Optional<DeviceRegisteration> findByPublicKey(String public_key);

	Optional<DeviceRegisteration> findByPatient(User patient);
    
}

