package com.digicare.digicare_rest_test.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.digicare.digicare_rest_test.model.Sensor;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Long> {

}
