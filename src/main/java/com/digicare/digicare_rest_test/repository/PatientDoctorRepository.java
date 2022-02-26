package com.digicare.digicare_rest_test.repository;



import java.util.List;
import java.util.Optional;
import javax.validation.constraints.NotBlank;


import com.digicare.digicare_rest_test.model.SensorPatientData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.digicare.digicare_rest_test.model.user.PatientDoctor;
import com.digicare.digicare_rest_test.model.user.PatientDoctorKey;



@Repository
public interface PatientDoctorRepository extends JpaRepository<PatientDoctor, PatientDoctorKey> {
	Optional<PatientDoctor> findById(@NotBlank PatientDoctorKey id);

	List<PatientDoctor> findByPatientId(@NotBlank Long patient_id);



}
