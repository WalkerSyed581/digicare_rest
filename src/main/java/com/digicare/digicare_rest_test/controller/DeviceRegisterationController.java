package com.digicare.digicare_rest_test.controller;

import java.util.List;

import com.digicare.digicare_rest_test.assembler.DeviceRegisterationModelAssembler;
import com.digicare.digicare_rest_test.model.user.DeviceRegisteration;
import com.digicare.digicare_rest_test.payload.ApiResponse;
import com.digicare.digicare_rest_test.payload.DeviceRegisterationRequest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.digicare.digicare_rest_test.exception.ReadingNotFoundException;
import com.digicare.digicare_rest_test.repository.DeviceRegisterationRepository;
import com.digicare.digicare_rest_test.security.CurrentUser;
import com.digicare.digicare_rest_test.security.UserPrincipal;
import com.digicare.digicare_rest_test.service.DeviceRegisterationService;

import org.springframework.hateoas.EntityModel;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import java.util.stream.Collectors;


@RestController
public class DeviceRegisterationController {

  private final DeviceRegisterationRepository repository;

  private final DeviceRegisterationModelAssembler assembler;

  @Autowired
	private DeviceRegisterationService deviceRegisterationService;

  DeviceRegisterationController(DeviceRegisterationRepository repository, DeviceRegisterationModelAssembler assembler) {
    this.repository = repository;
    this.assembler = assembler;
  }


  @GetMapping("/devices")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public CollectionModel<EntityModel<DeviceRegisteration>> all() {
    List<EntityModel<DeviceRegisteration>> SensorPatientDatas = repository.findAll().stream() //
        .map(assembler::toModel) //
        .collect(Collectors.toList());

    return CollectionModel.of(SensorPatientDatas, linkTo(methodOn(SensorPatientDataController.class).all()).withSelfRel());
  }

  @PostMapping("/devices")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public DeviceRegisteration newSensorPatientData(@RequestBody DeviceRegisterationRequest newReading,@CurrentUser UserPrincipal currentUser) {
		return deviceRegisterationService.addDevice(newReading, currentUser);
  }

  // Single item
  
  // @GetMapping("/readings/{patient_id}/{sensor_id}/{timestamp}")
  @GetMapping("/devices/{id}")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public EntityModel<DeviceRegisteration> one(@PathVariable Long id) {
      DeviceRegisteration DeviceRegisteration = repository.findById(id) //
        .orElseThrow(() -> new ReadingNotFoundException(id));

    return assembler.toModel(DeviceRegisteration);
  }	
  
	

  @DeleteMapping("/devices/{id}")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ApiResponse deleteReading(@PathVariable Long id,@CurrentUser UserPrincipal currentUser) {
    return deviceRegisterationService.deleteDevice(id,currentUser);
  }
}
