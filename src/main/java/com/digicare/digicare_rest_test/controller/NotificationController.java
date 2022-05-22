package com.digicare.digicare_rest_test.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.digicare.digicare_rest_test.assembler.NotificationModelAssembler;
import com.digicare.digicare_rest_test.exception.UserNotFoundException;
import com.digicare.digicare_rest_test.model.user.Notification;
import com.digicare.digicare_rest_test.payload.ApiResponse;
import com.digicare.digicare_rest_test.payload.NotificationRequest;
import com.digicare.digicare_rest_test.payload.SensorRequest;
import com.digicare.digicare_rest_test.repository.NotificationRepository;
import com.digicare.digicare_rest_test.security.CurrentUser;
import com.digicare.digicare_rest_test.security.UserPrincipal;
import com.digicare.digicare_rest_test.service.NotificationService;
import com.digicare.digicare_rest_test.service.SensorService;

import org.springframework.hateoas.EntityModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import java.util.stream.Collectors;


@RestController
public class NotificationController {

  private final NotificationRepository repository;

  private final NotificationModelAssembler assembler;

  @Autowired
  NotificationService notificationService;


  NotificationController(NotificationRepository repository,NotificationModelAssembler assembler) {
    this.repository = repository;
    this.assembler = assembler;
  }


  // Aggregate root
  // tag::get-aggregate-root[]
  @GetMapping("/notifications")
  public CollectionModel<EntityModel<Notification>> all() {
    List<EntityModel<Notification>> notifcations = repository.findAll().stream() //
        .map(assembler::toModel) //
        .collect(Collectors.toList());

    return CollectionModel.of(notifcations, linkTo(methodOn(NotificationController.class).all()).withSelfRel());
  }
  // end::get-aggregate-root[]

  @PostMapping("/notifications")
  public Notification newNotification(@RequestBody NotificationRequest newNotification) {
    return notificationService.addNotification(newNotification);
  }

  // Single item
  
  @GetMapping("/notifications/{id}")
  public EntityModel<Notification> one(@PathVariable Long id) {
    Notification Notification = repository.findById(id) //
        .orElseThrow(() -> new UserNotFoundException(id));

    return assembler.toModel(Notification);
  }


  @GetMapping("/notifications/{patient_id}")
  public CollectionModel<EntityModel<Notification>>  getByPatientId(@PathVariable Long patient_id) {
    List<EntityModel<Notification>> notifcations = repository.findByPatientIdOrderByTimestampDesc(patient_id).stream() //
        .map(assembler::toModel) //
        .collect(Collectors.toList());

    return CollectionModel.of(notifcations, linkTo(methodOn(NotificationController.class).all()).withSelfRel());
  }

  

  // @DeleteMapping("/sensors/{id}")
  // public ApiResponse deleteEmployee(@PathVariable Long id,@CurrentUser UserPrincipal currentUser) {
  //   return sensorService.deleteSensor(id,currentUser);
  // }
}
