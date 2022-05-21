package com.digicare.digicare_rest_test.assembler;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.digicare.digicare_rest_test.controller.NotificationController;
import com.digicare.digicare_rest_test.model.user.Notification;


@Component
public
class NotificationModelAssembler implements RepresentationModelAssembler<Notification, EntityModel<Notification>> {

  @Override
  public EntityModel<Notification> toModel(Notification notification) {

    return EntityModel.of(notification, //
        linkTo(methodOn(NotificationController.class).one(notification.getId())).withSelfRel(),
        linkTo(methodOn(NotificationController.class).all()).withRel("sensors"));
  }
}