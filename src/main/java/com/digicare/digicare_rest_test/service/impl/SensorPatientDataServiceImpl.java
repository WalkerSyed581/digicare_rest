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
        
        List<String> params = Arrays.asList(newReading.split("|"));
        if(StringUtils.isNumeric(params.get(0))){
            Long user_id = Long.parseLong(params.get(0));
            User user = userRepository.findById(user_id)
                    .orElseThrow(() -> new UserNotFoundException(user_id));
                
            

            DeviceRegisteration device = deviceRepository.findByPatient(user)
            .orElseThrow(() -> new DeviceNotFoundException(user));

            byte[] publicBytes = Base64.getDecoder().decode(device.getPublic_key());
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicBytes);
            
            KeyFactory keyFactory;
            PublicKey pubKey = null;

            try {
                keyFactory = KeyFactory.getInstance("RSA");
                pubKey = keyFactory.generatePublic(keySpec);
            } catch (Exception e) {
                return new ApiResponse(Boolean.FALSE, "Invalid Reading");
            }

            Cipher cipher;
            try {
                cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
                cipher.init(Cipher.DECRYPT_MODE, pubKey);
            } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e1) {
                return new ApiResponse(Boolean.FALSE, "Invalid Reading");
            }
            
            byte[] decipheredString = null;
            try {
                decipheredString = cipher.doFinal(params.get(1).getBytes());
            } catch (IllegalBlockSizeException | BadPaddingException e) {
                // TODO Auto-generated catch block
                return new ApiResponse(Boolean.FALSE, "Invalid Reading");
            }
            String readingStr = new String(decipheredString);

            try {
                CloudReadingRequest reading = objectMapper.readValue(readingStr, CloudReadingRequest.class);
                Iterator<Map.Entry<Long, Double>> itr = reading.readings.entrySet().iterator();
          
                while(itr.hasNext())
                {
                    Map.Entry<Long, Double> entry = itr.next();
                    SensorPatientData reading_entry = new SensorPatientData();
                    reading_entry.setPatient(user);
                    reading_entry.setReading(entry.getValue());
                    reading_entry.setSensor(sensorRepository.getById(entry.getKey()));
                    reading_entry.setTimestamp(Date.from(reading.getTimestamp().atZone(ZoneId.systemDefault()).toInstant()));

                    sensorPatientRepository.save(reading_entry);
                }
                

            } catch (Exception e) {
                // TODO Auto-generated catch block
                return new ApiResponse(Boolean.FALSE, "Invalid Reading");
            }

        }  else {
            return new ApiResponse(Boolean.FALSE, "Invalid Reading");
        }
           
        
        




        return new ApiResponse(Boolean.TRUE, "Reading Saved successfully  ");





    }

}
