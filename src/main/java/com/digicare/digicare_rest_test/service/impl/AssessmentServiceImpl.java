package com.digicare.digicare_rest_test.service.impl;


import com.digicare.digicare_rest_test.exception.AccessDeniedException;
import com.digicare.digicare_rest_test.exception.UnauthorizedException;
import com.digicare.digicare_rest_test.exception.UserNotFoundException;
import com.digicare.digicare_rest_test.model.Assessment;
import com.digicare.digicare_rest_test.model.role.RoleName;
import com.digicare.digicare_rest_test.payload.ApiResponse;
import com.digicare.digicare_rest_test.payload.AssessmentRequest;
import com.digicare.digicare_rest_test.repository.AssessmentRepository;
import com.digicare.digicare_rest_test.repository.UserRepository;
import com.digicare.digicare_rest_test.security.UserPrincipal;
import com.digicare.digicare_rest_test.service.AssessmentService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

@Service
public class AssessmentServiceImpl implements AssessmentService {

	@Autowired
    private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;


    @Autowired
	private AssessmentRepository assessmentRepository;
	

	@Override
	public Assessment addAssessment(AssessmentRequest newAssessment,UserPrincipal currentUser) {
		if (currentUser.getAuthorities().contains(new SimpleGrantedAuthority(RoleName.ROLE_DOCTOR.toString()))) {
            Assessment assessment = new Assessment();
		
			modelMapper.map(newAssessment,assessment);

			assessment.setPatient(userRepository.findById(newAssessment.getPatient_id()).orElseThrow(() -> new UserNotFoundException(newAssessment.getPatient_id())));
			assessment.setDoctor(userRepository.findById(currentUser.getId()).orElseThrow(() -> new UserNotFoundException(currentUser.getId())));


            return assessmentRepository.save(assessment);
        }

		ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, "You don't have permission to add an Assessment");
        throw new AccessDeniedException(apiResponse);

		
		

	}

	@Override
	public ApiResponse deleteAssessment(Long id, UserPrincipal currentUser) {
		// TODO Auto-generated method stub
		Assessment assessment = assessmentRepository.getById(id);
        if (!currentUser.getAuthorities().contains(new SimpleGrantedAuthority(RoleName.ROLE_DOCTOR.toString())) || currentUser.getId() != assessment.getDoctor().getId()) {
            ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, "You don't have permission to delete assessment of: " + id);
			throw new AccessDeniedException(apiResponse);

        }

		assessmentRepository.deleteById(id);

        return new ApiResponse(Boolean.TRUE, "You successfully deleted reading: " + id);
    
	}

	@Override
	public Assessment updateAssessment(AssessmentRequest newAssessment,Long id, UserPrincipal currentUser) {
		Assessment assessment = assessmentRepository.getById(id);
		if (currentUser.getAuthorities().contains(new SimpleGrantedAuthority(RoleName.ROLE_DOCTOR.toString()))) {
			assessment.setCg_instr(newAssessment.getCg_instr());
			assessment.setCondition(newAssessment.getCondition());
			assessment.setData_desc(newAssessment.getData_desc());
			assessment.setNotes(newAssessment.getNotes());
			assessment.setRecommendations(newAssessment.getCondition());

			return assessmentRepository.save(assessment);

		}

		ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, "You don't have permission to update assessment of: " + id);
		throw new UnauthorizedException(apiResponse);

        
	}


	
	
}
