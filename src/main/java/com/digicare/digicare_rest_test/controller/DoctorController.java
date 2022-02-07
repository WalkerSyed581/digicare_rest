package com.digicare.digicare_rest_test.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.digicare.digicare_rest_test.assembler.DoctorModelAssembler;
import com.digicare.digicare_rest_test.exception.UserNotFoundException;
import com.digicare.digicare_rest_test.model.user.Doctor;
import com.digicare.digicare_rest_test.repository.DoctorRepository;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import java.util.stream.Collectors;


@RestController
public class DoctorController {

  private final DoctorRepository repository;

  private final DoctorModelAssembler assembler;

  DoctorController(DoctorRepository repository,DoctorModelAssembler assembler) {
    this.repository = repository;
    this.assembler = assembler;
  }


  // Aggregate root
  // tag::get-aggregate-root[]
  @GetMapping("/doctors")
  public CollectionModel<EntityModel<Doctor>> all() {
    List<EntityModel<Doctor>> Doctors = repository.findAll().stream() //
        .map(assembler::toModel) //
        .collect(Collectors.toList());

    return CollectionModel.of(Doctors, linkTo(methodOn(DoctorController.class).all()).withSelfRel());
  }
  // end::get-aggregate-root[]

  @PostMapping("/doctors")
  public Doctor newDoctor(@RequestBody Doctor newUser) {
    return repository.save(newUser);
  }

  // Single item
  
  @GetMapping("/doctors/{id}")
  public EntityModel<Doctor> one(@PathVariable Long id) {
    Doctor Doctor = repository.findById(id) //
        .orElseThrow(() -> new UserNotFoundException(id));

    return assembler.toModel(Doctor);
  }

//  @PutMapping("/doctors/{id}")
//  public Doctor replaceEmployee(@RequestBody Doctor newDoctor, @PathVariable Long id) {
//    
//    return repository.findById(id)
//      .map(Doctor -> {
//        Doctor.setName(newDoctor.getName());
//        return repository.save(Doctor);
//      })
//      .orElseGet(() -> {
//    	  newDoctor.setId(id);
//    	  return repository.save(newDoctor);
//      });
//  }

  @DeleteMapping("/doctors/{id}")
  void deleteEmployee(@PathVariable Long id) {
    repository.deleteById(id);
  }
}
