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
import com.digicare.digicare_rest_test.payload.ApiResponse;
import com.digicare.digicare_rest_test.payload.SensorRequest;
import com.digicare.digicare_rest_test.repository.SensorRepository;
import com.digicare.digicare_rest_test.security.CurrentUser;
import com.digicare.digicare_rest_test.security.UserPrincipal;
import com.digicare.digicare_rest_test.service.SensorService;

import org.springframework.hateoas.EntityModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import java.util.stream.Collectors;


@RestController
public class SensorController {

  private final SensorRepository repository;

  private final SensorModelAssembler assembler;

  @Autowired
	private SensorService sensorService;

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
  public Sensor newSensor(@RequestBody SensorRequest newSensor) {
    return sensorService.addSensor(newSensor);
  }

  // Single item
  
  @GetMapping("/sensors/{id}")
  public EntityModel<Sensor> one(@PathVariable Long id) {
    Sensor Sensor = repository.findById(id) //
        .orElseThrow(() -> new UserNotFoundException(id));

    return assembler.toModel(Sensor);
  }

  @PutMapping("/sensors/{id}")
  public Sensor updateSensor(@RequestBody SensorRequest newSensor, @PathVariable Long id) {
    return sensorService.updateSensor(newSensor, id);
  }

  @DeleteMapping("/sensors/{id}")
  public ApiResponse deleteEmployee(@PathVariable Long id,@CurrentUser UserPrincipal currentUser) {
    return sensorService.deleteSensor(id,currentUser);
  }
}
