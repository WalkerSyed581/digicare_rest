package com.digicare.digicare_rest_test.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.digicare.digicare_rest_test.controller.AssessmentController;
import com.digicare.digicare_rest_test.model.Assessment;


@Component
public
class AssessmentModelAssembler implements RepresentationModelAssembler<Assessment, EntityModel<Assessment>> {

  @Override
  public EntityModel<Assessment> toModel(Assessment Assessment) {

    return EntityModel.of(Assessment, //
        linkTo(methodOn(AssessmentController.class).one(Assessment.getId())).withSelfRel(),
        linkTo(methodOn(AssessmentController.class).all()).withRel("assessments"));
  }
}