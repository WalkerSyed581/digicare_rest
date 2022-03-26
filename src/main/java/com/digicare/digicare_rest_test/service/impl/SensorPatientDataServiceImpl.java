package com.digicare.digicare_rest_test.service.impl;

import com.digicare.digicare_rest_test.model.user.*;
import com.digicare.digicare_rest_test.model.SensorPatientData;
import com.digicare.digicare_rest_test.model.role.*;
import com.digicare.digicare_rest_test.security.*;
import com.digicare.digicare_rest_test.payload.ApiResponse;
import com.digicare.digicare_rest_test.payload.ReadingRequest;
import com.digicare.digicare_rest_test.exception.*;
import com.digicare.digicare_rest_test.repository.SensorPatientRepository;
import com.digicare.digicare_rest_test.repository.SensorRepository;
import com.digicare.digicare_rest_test.repository.UserRepository;
import com.digicare.digicare_rest_test.service.SensorPatientDataService;

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

//    @Override
//    public ApiResponse giveAdmin(String username) {
//        User user = userRepository.getUserByName(username);
//        List<Role> roles = new ArrayList<>();
//        roles.add(roleRepository.findByName(RoleName.ROLE_ADMIN)
//                .orElseThrow(() -> new AppException("User role not set")));
//        roles.add(
//                roleRepository.findByName(RoleName.ROLE_USER).orElseThrow(() -> new AppException("User role not set")));
//        user.setRoles(roles);
//        userRepository.save(user);
//        return new ApiResponse(Boolean.TRUE, "You gave ADMIN role to user: " + username);
//    }
//
//    @Override
//    public ApiResponse removeAdmin(String username) {
//        User user = userRepository.getUserByName(username);
//        List<Role> roles = new ArrayList<>();
//        roles.add(
//                roleRepository.findByName(RoleName.ROLE_USER).orElseThrow(() -> new AppException("User role not set")));
//        user.setRoles(roles);
//        userRepository.save(user);
//        return new ApiResponse(Boolean.TRUE, "You took ADMIN role from user: " + username);
//    }

//    @Override
//    public UserProfile setOrUpdateInfo(UserPrincipal currentUser, InfoRequest infoRequest) {
//        User user = userRepository.findByUsername(currentUser.getUsername())
//                .orElseThrow(() -> new ResourceNotFoundException("User", "username", currentUser.getUsername()));
//        Geo geo = new Geo(infoRequest.getLat(), infoRequest.getLng());
//        Address address = new Address(infoRequest.getStreet(), infoRequest.getSuite(), infoRequest.getCity(),
//                infoRequest.getZipcode(), geo);
//        Company company = new Company(infoRequest.getCompanyName(), infoRequest.getCatchPhrase(), infoRequest.getBs());
//        if (user.getId().equals(currentUser.getId())
//                || currentUser.getAuthorities().contains(new SimpleGrantedAuthority(RoleName.ROLE_ADMIN.toString()))) {
//            user.setAddress(address);
//            user.setCompany(company);
//            user.setWebsite(infoRequest.getWebsite());
//            user.setPhone(infoRequest.getPhone());
//            User updatedUser = userRepository.save(user);
//
//            Long postCount = postRepository.countByCreatedBy(updatedUser.getId());
//
//            return new UserProfile(updatedUser.getId(), updatedUser.getUsername(),
//                    updatedUser.getFirstName(), updatedUser.getLastName(), updatedUser.getCreatedAt(),
//                    updatedUser.getEmail(), updatedUser.getAddress(), updatedUser.getPhone(), updatedUser.getWebsite(),
//                    updatedUser.getCompany(), postCount);
//        }
//
//        ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, "You don't have permission to update users profile", HttpStatus.FORBIDDEN);
//        throw new AccessDeniedException(apiResponse);
//    }
}
