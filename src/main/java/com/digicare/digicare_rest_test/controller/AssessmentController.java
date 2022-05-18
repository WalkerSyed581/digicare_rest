package com.digicare.digicare_rest_test.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.digicare.digicare_rest_test.assembler.AssessmentModelAssembler;
import com.digicare.digicare_rest_test.exception.UserNotFoundException;
import com.digicare.digicare_rest_test.model.Assessment;
import com.digicare.digicare_rest_test.payload.ApiResponse;
import com.digicare.digicare_rest_test.payload.AssessmentRequest;
import com.digicare.digicare_rest_test.repository.AssessmentRepository;
import com.digicare.digicare_rest_test.security.CurrentUser;
import com.digicare.digicare_rest_test.security.UserPrincipal;
import com.digicare.digicare_rest_test.service.AssessmentService;

import org.springframework.hateoas.EntityModel;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import java.util.stream.Collectors;


@RestController
public class AssessmentController {

  private final AssessmentRepository repository;

  private final AssessmentModelAssembler assembler;

  @Autowired
	private AssessmentService assessmentService;

  AssessmentController(AssessmentRepository repository,AssessmentModelAssembler assembler) {
    this.repository = repository;
    this.assembler = assembler;
  }


  // Aggregate root
  // tag::get-aggregate-root[]
  @GetMapping("/assessments")
  public CollectionModel<EntityModel<Assessment>> all() {
    List<EntityModel<Assessment>> Assessments = repository.findAllByOrderByTimestampDesc().stream() //
        .map(assembler::toModel) //
        .collect(Collectors.toList());

    return CollectionModel.of(Assessments, linkTo(methodOn(AssessmentController.class).all()).withSelfRel());
  }
  // end::get-aggregate-root[]

  @PostMapping("/assessments")
  public Assessment newAssessment(@RequestBody AssessmentRequest newAssessment,@CurrentUser UserPrincipal currentUser) {
    return assessmentService.addAssessment(newAssessment,currentUser);
  }

  // Single item
  
  @GetMapping("/assessments/{id}")
  public EntityModel<Assessment> one(@PathVariable Long id) {
    Assessment Assessment = repository.findById(id) //
        .orElseThrow(() -> new UserNotFoundException(id));

    return assembler.toModel(Assessment);
  }

  @GetMapping("/assessments/{doctor_id}/{patient_id}")
  public CollectionModel<EntityModel<Assessment>> getAssessmentsByDoctorForPatient(@PathVariable Long doctor_id,@PathVariable Long patient_id) {
    List<EntityModel<Assessment>> Assessments = repository.findAllByDoctorIdAndPatientIdOrderByTimestampDesc(doctor_id,patient_id).stream() //
        .map(assembler::toModel) //
        .collect(Collectors.toList());

    return CollectionModel.of(Assessments, linkTo(methodOn(AssessmentController.class).all()).withSelfRel());
  }

  @PutMapping("/assessments/{id}")
  public Assessment replaceAssessment(@RequestBody AssessmentRequest newAssessment, @PathVariable Long id,@CurrentUser UserPrincipal currentUser){
    return assessmentService.updateAssessment(newAssessment, id, currentUser);
  }

  @DeleteMapping("/assessments/{id}")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ApiResponse deleteEmployee(@PathVariable Long id,@CurrentUser UserPrincipal currentUser) {
    return assessmentService.deleteAssessment(id,currentUser);
  }
}
