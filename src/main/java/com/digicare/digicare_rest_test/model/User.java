package com.digicare.digicare_rest_test.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="USER_TABLE")
public class User extends AbstractUser {

	  User() {}

	  User(String firstName,String lastName,String email,String password,
	      String phone_no,Date dob,Gender gender,String address,int age,String roles) {

	    this.setFirstName(firstName);
	    this.setLastName(lastName);
	    this.setEmail(email);
	    this.setPassword(password);
	    this.setPhone_no(phone_no);
	    this.setDob(dob);
	    this.setGender(gender);
	    this.setAddress(address);
	    this.setAge(age);
	    this.setRoles(roles);
	    
	  }
}
