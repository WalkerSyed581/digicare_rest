package com.digicare.digicare_rest_test.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.digicare.digicare_rest_test.controller.PatientController;
import com.digicare.digicare_rest_test.model.user.Patient;


@Component
public
class PatientModelAssembler implements RepresentationModelAssembler<Patient, EntityModel<Patient>> {

  @Override
  public EntityModel<Patient> toModel(Patient patient) {

    return EntityModel.of(patient, //
        linkTo(methodOn(PatientController.class).one(patient.getId())).withSelfRel(),
        linkTo(methodOn(PatientController.class).all()).withRel("patients"));
  }
}