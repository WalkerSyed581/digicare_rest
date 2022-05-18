package com.digicare.digicare_rest_test.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.digicare.digicare_rest_test.model.user.DeviceRegisteration;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.digicare.digicare_rest_test.controller.SensorPatientDataController;


@Component
public
class DeviceRegisterationModelAssembler implements RepresentationModelAssembler<DeviceRegisteration, EntityModel<DeviceRegisteration>> {

  @Override
  public EntityModel<DeviceRegisteration> toModel(DeviceRegisteration SensorPatientData) {
	  Long id = SensorPatientData.getId();
	  
	  return EntityModel.of(SensorPatientData, //
        linkTo(methodOn(SensorPatientDataController.class).one(id)).withSelfRel(),
        linkTo(methodOn(SensorPatientDataController.class).all()).withRel("sensorPatientData"));
  }
}