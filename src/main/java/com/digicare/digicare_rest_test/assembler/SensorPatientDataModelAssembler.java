package com.digicare.digicare_rest_test.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.digicare.digicare_rest_test.model.SensorPatientData;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.digicare.digicare_rest_test.controller.SensorPatientDataController;


@Component
public
class SensorPatientDataModelAssembler implements RepresentationModelAssembler<SensorPatientData, EntityModel<SensorPatientData>> {

  @Override
  public EntityModel<SensorPatientData> toModel(SensorPatientData SensorPatientData) {
	  Long id = SensorPatientData.getId();
	  
	  return EntityModel.of(SensorPatientData, //
        linkTo(methodOn(SensorPatientDataController.class).one(id)).withSelfRel(),
        linkTo(methodOn(SensorPatientDataController.class).all()).withRel("sensorPatientData"));
  }
}