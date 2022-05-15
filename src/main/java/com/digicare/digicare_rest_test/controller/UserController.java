package com.digicare.digicare_rest_test.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;

import com.digicare.digicare_rest_test.assembler.UserModelAssembler;
import com.digicare.digicare_rest_test.exception.UserNotFoundException;
import com.digicare.digicare_rest_test.model.role.Role;
import com.digicare.digicare_rest_test.model.role.RoleName;
import com.digicare.digicare_rest_test.model.user.User;
import com.digicare.digicare_rest_test.payload.ApiResponse;
import com.digicare.digicare_rest_test.payload.SignUpRequest;
import com.digicare.digicare_rest_test.repository.RoleRepository;
import com.digicare.digicare_rest_test.repository.UserRepository;
import com.digicare.digicare_rest_test.security.CurrentUser;
import com.digicare.digicare_rest_test.security.UserPrincipal;
import com.digicare.digicare_rest_test.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import java.util.stream.Collectors;


@RestController
public class UserController {

  private final UserRepository repository;

  private final UserModelAssembler assembler;

  @Autowired
	private UserService userService;

  @Autowired
	private RoleRepository roleRepository;


  @Autowired
	private ModelMapper modelMapper;

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
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public User newUser(@RequestBody SignUpRequest newUser) {
    return userService.addUser(newUser);
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

 @GetMapping("/users/role/{role}")
 public CollectionModel<EntityModel<User>> byRole(@PathVariable String role) {
   Role userRole = roleRepository.findByName(RoleName.valueOf(role));


   List<EntityModel<User>> users = repository.findByRolesIn(Arrays.asList(userRole)).stream() //
   .map(assembler::toModel) //
   .collect(Collectors.toList());

  return CollectionModel.of(users, linkTo(methodOn(UserController.class).all()).withSelfRel());
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
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ApiResponse deleteEmployee(@PathVariable Long id,@CurrentUser UserPrincipal currentUser) {
    return userService.deleteUser(id,currentUser);
  }
}
