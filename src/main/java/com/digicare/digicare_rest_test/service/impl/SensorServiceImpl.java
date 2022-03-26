package com.digicare.digicare_rest_test.service.impl;


import com.digicare.digicare_rest_test.model.Sensor;
import com.digicare.digicare_rest_test.payload.ApiResponse;
import com.digicare.digicare_rest_test.payload.SensorRequest;
import com.digicare.digicare_rest_test.repository.SensorRepository;
import com.digicare.digicare_rest_test.repository.UserRepository;
import com.digicare.digicare_rest_test.security.UserPrincipal;
import com.digicare.digicare_rest_test.service.SensorService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SensorServiceImpl implements SensorService {
	@Autowired
    private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;


    @Autowired
	private SensorRepository sensorRepository;
	

	@Override
	public Sensor addSensor(SensorRequest newSensor) {
		Sensor sensor = new Sensor();
	
		modelMapper.map(newSensor,sensor);

		

		return sensorRepository.save(sensor);
	}

	@Override
	public ApiResponse deleteSensor(Long id, UserPrincipal currentUser) {
		sensorRepository.deleteById(id);

        return new ApiResponse(Boolean.TRUE, "You successfully deleted reading: " + id);
    
	}

	@Override
	public Sensor updateSensor(SensorRequest newSensor,Long id) {
		Sensor sensor = sensorRepository.getById(id);
		sensor.setData_desc(newSensor.getData_desc());
		sensor.setName(newSensor.getName());

		return sensorRepository.save(sensor);


        
	}


	
	
}
