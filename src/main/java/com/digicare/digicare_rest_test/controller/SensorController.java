package com.digicare.digicare_rest_test.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.digicare.digicare_rest_test.assembler.SensorModelAssembler;
import com.digicare.digicare_rest_test.exception.UserNotFoundException;
import com.digicare.digicare_rest_test.model.Sensor;
import com.digicare.digicare_rest_test.repository.SensorRepository;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import java.util.stream.Collectors;


@RestController
public class SensorController {

  private final SensorRepository repository;

  private final SensorModelAssembler assembler;

  SensorController(SensorRepository repository,SensorModelAssembler assembler) {
    this.repository = repository;
    this.assembler = assembler;
  }


  // Aggregate root
  // tag::get-aggregate-root[]
  @GetMapping("/sensors")
  public CollectionModel<EntityModel<Sensor>> all() {
    List<EntityModel<Sensor>> Sensors = repository.findAll().stream() //
        .map(assembler::toModel) //
        .collect(Collectors.toList());

    return CollectionModel.of(Sensors, linkTo(methodOn(SensorController.class).all()).withSelfRel());
  }
  // end::get-aggregate-root[]

  @PostMapping("/sensors")
  public Sensor newSensor(@RequestBody Sensor newUser) {
    return repository.save(newUser);
  }

  // Single item
  
  @GetMapping("/sensors/{id}")
  public EntityModel<Sensor> one(@PathVariable Long id) {
    Sensor Sensor = repository.findById(id) //
        .orElseThrow(() -> new UserNotFoundException(id));

    return assembler.toModel(Sensor);
  }

  @PutMapping("/sensors/{id}")
  public Sensor replaceEmployee(@RequestBody Sensor newSensor, @PathVariable Long id) {
    
    return repository.findById(id)
      .map(Sensor -> {
        Sensor.setName(newSensor.getName());
        return repository.save(Sensor);
      })
      .orElseGet(() -> {
    	  newSensor.setId(id);
    	  return repository.save(newSensor);
      });
  }

  @DeleteMapping("/sensors/{id}")
  void deleteEmployee(@PathVariable Long id) {
    repository.deleteById(id);
  }
}
