package com.digicare.digicare_rest_test.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.digicare.digicare_rest_test.controller.DoctorController;
import com.digicare.digicare_rest_test.model.Doctor;


@Component
public
class DoctorModelAssembler implements RepresentationModelAssembler<Doctor, EntityModel<Doctor>> {

  @Override
  public EntityModel<Doctor> toModel(Doctor Doctor) {

    return EntityModel.of(Doctor, //
        linkTo(methodOn(DoctorController.class).one(Doctor.getId())).withSelfRel(),
        linkTo(methodOn(DoctorController.class).all()).withRel("doctors"));
  }
}