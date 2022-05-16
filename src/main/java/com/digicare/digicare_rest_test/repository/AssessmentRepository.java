package com.digicare.digicare_rest_test.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.digicare.digicare_rest_test.model.Assessment;

@Repository
public interface AssessmentRepository extends JpaRepository<Assessment, Long> {

	List<Assessment> findAllByDoctorIdAndPatientIdOrderByTimestampDesc(@NotBlank Long doctor_id,@NotBlank Long patient_id);

	List<Assessment> findAllByOrderByTimestampDesc();

}
