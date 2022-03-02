package com.digicare.digicare_rest_test.service.impl;

import com.digicare.digicare_rest_test.model.user.*;
import com.digicare.digicare_rest_test.model.role.*;
import com.digicare.digicare_rest_test.security.*;
import com.digicare.digicare_rest_test.payload.ApiResponse;
import com.digicare.digicare_rest_test.payload.SignUpRequest;
import com.digicare.digicare_rest_test.exception.*;
import com.digicare.digicare_rest_test.payload.UserIdentityAvailability;
import com.digicare.digicare_rest_test.repository.AddressRepository;
import com.digicare.digicare_rest_test.repository.CaregiverRepository;
import com.digicare.digicare_rest_test.repository.DoctorRepository;
import com.digicare.digicare_rest_test.repository.PatientRepository;
import com.digicare.digicare_rest_test.repository.RoleRepository;
import com.digicare.digicare_rest_test.repository.UserRepository;
import com.digicare.digicare_rest_test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.authority.SimpleGrantedAuthority;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;


    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private CaregiverRepository caregiverRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserIdentityAvailability checkUsernameAvailability(String username) {
        Boolean isAvailable = !userRepository.existsByUsername(username);
        return new UserIdentityAvailability(isAvailable);
    }

    @Override
    public UserIdentityAvailability checkEmailAvailability(String email) {
        Boolean isAvailable = !userRepository.existsByEmail(email);
        return new UserIdentityAvailability(isAvailable);
    }

    @Override
    public User addUser(User user,int role) {

        if (userRepository.existsByEmail(user.getEmail())) {
            ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, "Email is already taken");
            throw new BadRequestException(apiResponse);
        }
        
        RoleName user_rName = RoleName.values()[role - 1];

        if(user_rName.equals(RoleName.ROLE_PATIENT)){
            Patient patient = new Patient();
            patientRepository.save(patient);
            user.setPatient(patient);

            
        } else if(user_rName.equals(RoleName.ROLE_DOCTOR)){
            Doctor doctor = new Doctor();
            doctorRepository.save(doctor);
            user.setDoctor(doctor);

        } else{
            Caregiver cg = new Caregiver();
            caregiverRepository.save(cg);
            user.setCaregiver(cg);
        }
        user.setAddress(addressRepository.save(user.getAddress()));
        user.setRoles(Arrays.asList((roleRepository.findByName(user_rName))));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User newUser, Long id, UserPrincipal currentUser) {
        User user = userRepository.getById(id);
        if (user.getId().equals(currentUser.getId())
                || currentUser.getAuthorities().contains(new SimpleGrantedAuthority(RoleName.ROLE_ADMIN.toString()))) {
            user.setFirstName(newUser.getFirstName());
            user.setLastName(newUser.getLastName());
            user.setPassword(passwordEncoder.encode(newUser.getPassword()));

            return userRepository.save(user);

        }

        ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, "You don't have permission to update profile of: " + id);
        throw new AccessDeniedException(apiResponse);

    }

    @Override
    public ApiResponse deleteUser(Long id,UserPrincipal currentUser) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        if (!user.getId().equals(currentUser.getId()) || !currentUser.getAuthorities()
				.contains(new SimpleGrantedAuthority(RoleName.ROLE_ADMIN.toString()))) {
			ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, "You don't have permission to delete profile of: " + id);
			throw new AccessDeniedException(apiResponse);
		}

        userRepository.deleteById(user.getId());

        return new ApiResponse(Boolean.TRUE, "You successfully deleted profile of: " + id);
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
