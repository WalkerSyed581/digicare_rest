package com.digicare.digicare_rest_test.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.digicare.digicare_rest_test.assembler.PatientModelAssembler;
import com.digicare.digicare_rest_test.exception.UserNotFoundException;
import com.digicare.digicare_rest_test.model.user.Patient;
import com.digicare.digicare_rest_test.repository.PatientRepository;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import java.util.stream.Collectors;


@RestController
public class PatientController {

  private final PatientRepository repository;

  private final PatientModelAssembler assembler;

  PatientController(PatientRepository repository,PatientModelAssembler assembler) {
    this.repository = repository;
    this.assembler = assembler;
  }


  // Aggregate root
  // tag::get-aggregate-root[]
  @GetMapping("/patients")
  public CollectionModel<EntityModel<Patient>> all() {
    List<EntityModel<Patient>> patients = repository.findAll().stream() //
        .map(assembler::toModel) //
        .collect(Collectors.toList());

    return CollectionModel.of(patients, linkTo(methodOn(PatientController.class).all()).withSelfRel());
  }
  // end::get-aggregate-root[]

  @PostMapping("/patients")
  public Patient newPatient(@RequestBody Patient newUser) {
    return repository.save(newUser);
  }

  // Single item
  
  @GetMapping("/patients/{id}")
  public EntityModel<Patient> one(@PathVariable Long id) {
    Patient patient = repository.findById(id) //
        .orElseThrow(() -> new UserNotFoundException(id));

    return assembler.toModel(patient);
  }

//  @PutMapping("/patients/{id}")
//  public Patient replaceEmployee(@RequestBody Patient newPatient, @PathVariable Long id) {
//    
//    return repository.findById(id)
//      .map(patient -> {
//        patient.setName(newPatient.getName());
//        return repository.save(patient);
//      })
//      .orElseGet(() -> {
//    	  newPatient.setId(id);
//    	  return repository.save(newPatient);
//      });
//  }

  @DeleteMapping("/patients/{id}")
  void deleteEmployee(@PathVariable Long id) {
    repository.deleteById(id);
  }
}
