package com.digicare.digicare_rest_test.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.digicare.digicare_rest_test.model.role.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
