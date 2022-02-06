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
import com.digicare.digicare_rest_test.repository.AssessmentRepository;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import java.util.stream.Collectors;


@RestController
public class AssessmentController {

  private final AssessmentRepository repository;

  private final AssessmentModelAssembler assembler;

  AssessmentController(AssessmentRepository repository,AssessmentModelAssembler assembler) {
    this.repository = repository;
    this.assembler = assembler;
  }


  // Aggregate root
  // tag::get-aggregate-root[]
  @GetMapping("/assessments")
  public CollectionModel<EntityModel<Assessment>> all() {
    List<EntityModel<Assessment>> Assessments = repository.findAll().stream() //
        .map(assembler::toModel) //
        .collect(Collectors.toList());

    return CollectionModel.of(Assessments, linkTo(methodOn(AssessmentController.class).all()).withSelfRel());
  }
  // end::get-aggregate-root[]

  @PostMapping("/assessments")
  public Assessment newAssessment(@RequestBody Assessment newUser) {
    return repository.save(newUser);
  }

  // Single item
  
  @GetMapping("/assessments/{id}")
  public EntityModel<Assessment> one(@PathVariable Long id) {
    Assessment Assessment = repository.findById(id) //
        .orElseThrow(() -> new UserNotFoundException(id));

    return assembler.toModel(Assessment);
  }

  @PutMapping("/assessments/{id}")
  public Assessment replaceAssessment(@RequestBody Assessment newAssessment, @PathVariable Long id) {
    
    return repository.findById(id)
      .map(Assessment -> {
        Assessment.setCondition(newAssessment.getCondition());
        Assessment.setData_desc(newAssessment.getData_desc());
        Assessment.setDoctor(newAssessment.getDoctor());
        Assessment.setNotes(newAssessment.getNotes());
        Assessment.setPatient(newAssessment.getPatient());
        Assessment.setRecommendations(newAssessment.getRecommendations());
        Assessment.setCg_instr(newAssessment.getCg_instr());
        return repository.save(Assessment);
      })
      .orElseGet(() -> {
    	  newAssessment.setId(id);
    	  return repository.save(newAssessment);
      });
  }

  @DeleteMapping("/assessments/{id}")
  void deleteEmployee(@PathVariable Long id) {
    repository.deleteById(id);
  }
}
