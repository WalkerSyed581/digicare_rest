package com.digicare.digicare_rest_test.service.impl;

import com.digicare.digicare_rest_test.model.user.*;
import com.digicare.digicare_rest_test.model.SensorPatientData;
import com.digicare.digicare_rest_test.model.role.*;
import com.digicare.digicare_rest_test.security.*;
import com.digicare.digicare_rest_test.payload.ApiResponse;
import com.digicare.digicare_rest_test.payload.CloudReadingRequest;
import com.digicare.digicare_rest_test.payload.ReadingRequest;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.digicare.digicare_rest_test.exception.*;
import com.digicare.digicare_rest_test.repository.DeviceRegisterationRepository;
import com.digicare.digicare_rest_test.repository.SensorPatientRepository;
import com.digicare.digicare_rest_test.repository.SensorRepository;
import com.digicare.digicare_rest_test.repository.UserRepository;
import com.digicare.digicare_rest_test.service.SensorPatientDataService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import org.springframework.security.core.authority.SimpleGrantedAuthority;


@Service
public class SensorPatientDataServiceImpl implements SensorPatientDataService {
    @Autowired
    private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;


    @Autowired
	private SensorPatientRepository sensorPatientRepository;

    @Autowired
	private DeviceRegisterationRepository deviceRepository;
	
	@Autowired
	private SensorRepository sensorRepository;

    
    @Override
	public SensorPatientData addReading(ReadingRequest readingRequest, UserPrincipal currentUser) {

		SensorPatientData reading = new SensorPatientData();

		modelMapper.map(readingRequest,reading);
        System.out.println((userRepository.findById(readingRequest.getPatient_id())));
		reading.setPatient(userRepository.findById(readingRequest.getPatient_id()).orElseThrow(() -> new UserNotFoundException(readingRequest.getPatient_id())));
		reading.setSensor(sensorRepository.findById(readingRequest.getSensor_id()).orElseThrow(() -> new SensorNotFoundException(readingRequest.getPatient_id())));


		SensorPatientData newReading = sensorPatientRepository.save(reading);
		return newReading;
	}

    @Override
    public SensorPatientData updateReading(ReadingRequest updatedReading, Long id, UserPrincipal currentUser) {
        SensorPatientData reading = sensorPatientRepository.getById(id);
        if (currentUser.getAuthorities().contains(new SimpleGrantedAuthority(RoleName.ROLE_ADMIN.toString()))) {
            reading.setReading(updatedReading.getReading());
            reading.setTimestamp(updatedReading.getTimestamp());

            return sensorPatientRepository.save(reading);

        }

        ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, "You don't have permission to update reading of: " + id);
        throw new AccessDeniedException(apiResponse);

    }

    @Override
    public ApiResponse deleteReading(Long id,UserPrincipal currentUser) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        if (!user.getId().equals(currentUser.getId()) || !currentUser.getAuthorities()
				.contains(new SimpleGrantedAuthority(RoleName.ROLE_ADMIN.toString()))) {
			ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, "You don't have permission to delete profile of: " + id);
			throw new AccessDeniedException(apiResponse);
		}

        sensorPatientRepository.deleteById(id);

        return new ApiResponse(Boolean.TRUE, "You successfully deleted reading: " + id);
    }

    @Override
    public ApiResponse verifyAndAddReading(String newReading) {
        // TODO Verify and Add Reading

        ObjectMapper objectMapper = new ObjectMapper();
        
        List<String> params = Arrays.asList(newReading.split("\\|"));
        if(StringUtils.isNumeric(params.get(0))){
            Long user_id = Long.parseLong(params.get(0));
            User user = userRepository.findById(user_id)
                    .orElseThrow(() -> new UserNotFoundException(user_id));
            

            DeviceRegisteration device = deviceRepository.findByPatient(user)
            .orElseThrow(() -> new DeviceNotFoundException(user));

            try {

                byte[] publicBytes = Base64.getDecoder().decode(device.getPublic_key());
                X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicBytes);

                KeyFactory keyFactory = KeyFactory.getInstance("RSA");
                PublicKey pubKey = (PublicKey) keyFactory.generatePublic(keySpec);

                Signature sign = Signature.getInstance("SHA256withRSA");

                sign.initVerify(pubKey);
                sign.update(params.get(0).getBytes());
                Boolean sign_ver = sign.verify(Base64.getDecoder().decode(params.get(1)));
    

                if(sign_ver){
                    CloudReadingRequest reading = objectMapper.readValue(params.get(2), CloudReadingRequest.class);
            
                    SensorPatientData temp_entry = new SensorPatientData();
                    temp_entry.setPatient(user);
                    temp_entry.setReading(reading.getTemperature());
                    temp_entry.setSensor(sensorRepository.getById((long) 14));
                    temp_entry.setTimestamp(reading.getTimestamp());

                    sensorPatientRepository.save(temp_entry);

                    SensorPatientData spo2_entry = new SensorPatientData();
                    spo2_entry.setPatient(user);
                    spo2_entry.setReading(reading.getSpo2());
                    spo2_entry.setSensor(sensorRepository.getById((long) 13));
                    spo2_entry.setTimestamp(reading.getTimestamp());

                    sensorPatientRepository.save(spo2_entry);

                    SensorPatientData heart_entry = new SensorPatientData();
                    heart_entry.setPatient(user);
                    heart_entry.setReading(reading.getHeart_rate());
                    heart_entry.setSensor(sensorRepository.getById((long) 14));
                    heart_entry.setTimestamp(reading.getTimestamp());

                    sensorPatientRepository.save(heart_entry);
                    
                    
                } else {
                    return new ApiResponse(Boolean.FALSE, "Invalid Reading");
                }
                

            } catch (Exception e) {
                // TODO Auto-generated catch block
                System.out.print(e);
                return new ApiResponse(Boolean.FALSE, "Invalid Reading");
            }

        }  else {
            return new ApiResponse(Boolean.FALSE, "Invalid Reading");
        }
           
        
        




        return new ApiResponse(Boolean.TRUE, "Reading Saved successfully  ");





    }

}
