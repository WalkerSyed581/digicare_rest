package com.digicare.digicare_rest_test.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import javax.validation.constraints.NotBlank;
import java.util.Optional;

import com.digicare.digicare_rest_test.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByEmail(@NotBlank String email);
	
}
