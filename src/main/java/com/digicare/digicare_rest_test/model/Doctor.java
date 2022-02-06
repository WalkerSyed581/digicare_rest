package com.digicare.digicare_rest_test.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.SecondaryTable;
import javax.persistence.Column;

@Entity(name = "Doctor")
@SecondaryTable(name="USER_TABLE")
public class Doctor extends AbstractUser {

	@Column(length = 11)
  	private String emergencey_contact;
	
	public Doctor() {
		// TODO Auto-generated constructor stub
	}

	public Doctor(String firstName, String lastName, String email, String password, String phone_no, Date dob,
			Gender gender, String address, int age,String roles,String emergencey_contact) {

		// TODO Auto-generated constructor stub	
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
		this.emergencey_contact = emergencey_contact;
	}

	public String getEmergencey_contact() {
		return emergencey_contact;
	}

	public void setEmergencey_contact(String emergencey_contact) {
		this.emergencey_contact = emergencey_contact;
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