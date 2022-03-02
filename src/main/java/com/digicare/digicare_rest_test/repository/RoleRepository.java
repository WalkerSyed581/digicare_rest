package com.digicare.digicare_rest_test.repository;


import com.digicare.digicare_rest_test.model.role.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.digicare.digicare_rest_test.model.role.Role;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
   Role findByName(RoleName name);

}
