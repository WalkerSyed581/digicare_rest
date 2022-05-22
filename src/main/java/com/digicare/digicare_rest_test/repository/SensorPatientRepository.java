package com.digicare.digicare_rest_test.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Repository;

import com.digicare.digicare_rest_test.model.SensorPatientData;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface SensorPatientRepository extends JpaRepository<SensorPatientData, Long> {
    List<SensorPatientData> findByPatientIdAndSensorId(Long patient_id, Long sensor_id);

	List<SensorPatientData> findByPatientId(Long patient_id);
}
