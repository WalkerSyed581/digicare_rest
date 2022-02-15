package com.digicare.digicare_rest_test.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.digicare.digicare_rest_test.model.user.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
