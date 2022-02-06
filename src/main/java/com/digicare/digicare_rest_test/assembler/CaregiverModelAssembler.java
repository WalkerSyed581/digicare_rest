package com.digicare.digicare_rest_test.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.digicare.digicare_rest_test.controller.CaregiverController;
import com.digicare.digicare_rest_test.model.Caregiver;


@Component
public
class CaregiverModelAssembler implements RepresentationModelAssembler<Caregiver, EntityModel<Caregiver>> {

  @Override
  public EntityModel<Caregiver> toModel(Caregiver Caregiver) {

    return EntityModel.of(Caregiver, //
        linkTo(methodOn(CaregiverController.class).one(Caregiver.getId())).withSelfRel(),
        linkTo(methodOn(CaregiverController.class).all()).withRel("caregivers"));
  }
}