package com.digicare.digicare_rest_test.repository;


import java.util.Optional;

import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.digicare.digicare_rest_test.model.user.Caregiver;

@Repository
public interface CaregiverRepository extends JpaRepository<Caregiver, Long> {
	Optional<Caregiver> findByEmail(@NotBlank String email);


}
