package com.digicare.digicare_rest_test.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.digicare.digicare_rest_test.assembler.UserModelAssembler;
import com.digicare.digicare_rest_test.exception.UserNotFoundException;
import com.digicare.digicare_rest_test.model.user.User;
import com.digicare.digicare_rest_test.repository.UserRepository;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import java.util.stream.Collectors;


@RestController
public class UserController {

  private final UserRepository repository;

  private final UserModelAssembler assembler;

  UserController(UserRepository repository,UserModelAssembler assembler) {
    this.repository = repository;
    this.assembler = assembler;
  }


  // Aggregate root
  // tag::get-aggregate-root[]
  @GetMapping("/users")
  public CollectionModel<EntityModel<User>> all() {
    List<EntityModel<User>> users = repository.findAll().stream() //
        .map(assembler::toModel) //
        .collect(Collectors.toList());

    return CollectionModel.of(users, linkTo(methodOn(UserController.class).all()).withSelfRel());
  }
  // end::get-aggregate-root[]

  @PostMapping("/users")
  public User newUser(@RequestBody User newUser) {
    return repository.save(newUser);
  }

  // Single item
  
  @GetMapping("/users/{id}")
  public EntityModel<User> one(@PathVariable Long id) {
    User User = repository.findById(id) //
        .orElseThrow(() -> new UserNotFoundException(id));

    return assembler.toModel(User);
  }
  
//Single item
  
 @GetMapping("/users/email/{email}")
 public EntityModel<User> byEmail(@PathVariable String email) {
   User User = repository.findByEmail(email) //
       .orElseThrow(() -> new UserNotFoundException(email));

   return assembler.toModel(User);
 }

//  @PutMapping("/users/{id}")
//  public User replaceEmployee(@RequestBody User newUser, @PathVariable Long id) {
//    
//    return repository.findById(id)
//      .map(User -> {
//        User.setName(newUser.getName());
//        return repository.save(User);
//      })
//      .orElseGet(() -> {
//    	  newUser.setId(id);
//    	  return repository.save(newUser);
//      });
//  }

  @DeleteMapping("/users/{id}")
  void deleteEmployee(@PathVariable Long id) {
    repository.deleteById(id);
  }
}
