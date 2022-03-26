package com.digicare.digicare_rest_test.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.digicare.digicare_rest_test.model.role.Role;
import com.digicare.digicare_rest_test.model.user.User;

import javax.validation.constraints.NotBlank;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Boolean existsByUsername(@NotBlank String username);

	Boolean existsByEmail(@NotBlank String email);
	
	Optional<User> findByUsername(@NotBlank String username);
	
	Optional<User> findByEmail(@NotBlank String email);

    List<User> findByRolesIn(@NotBlank List<Role> roles);
	
	
}
