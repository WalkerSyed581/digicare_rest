package com.digicare.digicare_rest_test.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.digicare.digicare_rest_test.model.SensorPatientData;


@Repository
public interface SensorPatientDataRepository extends JpaRepository<SensorPatientData, Long> {
	Optional<SensorPatientData> findById(Long id);

}
