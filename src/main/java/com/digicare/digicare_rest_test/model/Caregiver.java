package com.digicare.digicare_rest_test.model;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.persistence.SecondaryTable;
import javax.persistence.JoinColumn;

@Entity(name = "Caregiver")
@SecondaryTable(name="USER_TABLE")
public class Caregiver extends AbstractUser {
 
	
	@Column(length = 11)
  	private String relationship;
	
	
	@OneToOne(optional = false)
	@JoinColumn(name = "patient_id", referencedColumnName = "id")
	private Patient patient;


	public Caregiver() {
		// TODO Auto-generated constructor stub
	}


	public Caregiver(String firstName, String lastName, String email, String password, String phone_no, Date dob,
			Gender gender, String address, int age,String roles,String relationship,Patient patient) {
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
		this.relationship = relationship;
		this.patient = patient;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	
	
	
	
	
	
	

	  
}