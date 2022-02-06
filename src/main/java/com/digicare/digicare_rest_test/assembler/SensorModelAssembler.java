package com.digicare.digicare_rest_test.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.digicare.digicare_rest_test.controller.SensorController;
import com.digicare.digicare_rest_test.model.Sensor;


@Component
public
class SensorModelAssembler implements RepresentationModelAssembler<Sensor, EntityModel<Sensor>> {

  @Override
  public EntityModel<Sensor> toModel(Sensor Sensor) {

    return EntityModel.of(Sensor, //
        linkTo(methodOn(SensorController.class).one(Sensor.getId())).withSelfRel(),
        linkTo(methodOn(SensorController.class).all()).withRel("sensors"));
  }
}