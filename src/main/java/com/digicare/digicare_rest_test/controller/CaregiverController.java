package com.digicare.digicare_rest_test.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.digicare.digicare_rest_test.assembler.CaregiverModelAssembler;
import com.digicare.digicare_rest_test.exception.UserNotFoundException;
import com.digicare.digicare_rest_test.model.Caregiver;
import com.digicare.digicare_rest_test.repository.CaregiverRepository;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import java.util.stream.Collectors;


@RestController
public class CaregiverController {

  private final CaregiverRepository repository;

  private final CaregiverModelAssembler assembler;

  CaregiverController(CaregiverRepository repository,CaregiverModelAssembler assembler) {
    this.repository = repository;
    this.assembler = assembler;
  }


  // Aggregate root
  // tag::get-aggregate-root[]
  @GetMapping("/caregivers")
  public CollectionModel<EntityModel<Caregiver>> all() {
    List<EntityModel<Caregiver>> caregivers = repository.findAll().stream() //
        .map(assembler::toModel) //
        .collect(Collectors.toList());

    return CollectionModel.of(caregivers, linkTo(methodOn(CaregiverController.class).all()).withSelfRel());
  }
  // end::get-aggregate-root[]

  @PostMapping("/caregivers")
  public Caregiver newCaregiver(@RequestBody Caregiver newUser) {
    return repository.save(newUser);
  }

  // Single item
  
  @GetMapping("/caregivers/{id}")
  public EntityModel<Caregiver> one(@PathVariable Long id) {
    Caregiver Caregiver = repository.findById(id) //
        .orElseThrow(() -> new UserNotFoundException(id));

    return assembler.toModel(Caregiver);
  }

  @PutMapping("/caregivers/{id}")
  public Caregiver replaceEmployee(@RequestBody Caregiver newCaregiver, @PathVariable Long id) {
    
    return repository.findById(id)
      .map(caregiver -> {
        caregiver.setName(newCaregiver.getName());
        return repository.save(caregiver);
      })
      .orElseGet(() -> {
    	  newCaregiver.setId(id);
    	  return repository.save(newCaregiver);
      });
  }

  @DeleteMapping("/caregivers/{id}")
  void deleteEmployee(@PathVariable Long id) {
    repository.deleteById(id);
  }
}
