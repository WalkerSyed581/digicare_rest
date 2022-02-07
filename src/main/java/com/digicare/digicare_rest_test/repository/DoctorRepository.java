package com.digicare.digicare_rest_test.repository;


import java.util.Optional;

import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.digicare.digicare_rest_test.model.user.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
	Optional<Doctor> findByEmail(@NotBlank String email);

}
