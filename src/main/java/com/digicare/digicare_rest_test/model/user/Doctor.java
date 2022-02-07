package com.digicare.digicare_rest_test.model.user;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Column;

@Entity(name = "doctor")
public class Doctor {
	
	@Id
    @Column(name = "user_id")
    private long userId;

    @OneToOne
    @PrimaryKeyJoinColumn(name="user_id", referencedColumnName="id")
    private User user;

	@Column(length = 11)
  	private String emergencey_contact;
	
	public Doctor() {
		// TODO Auto-generated constructor stub
	}

	public Doctor(User user,String emergencey_contact) {
		this.user = user;
		this.userId = user.getId();
		this.emergencey_contact = emergencey_contact;
	}

	public String getEmergencey_contact() {
		return emergencey_contact;
	}

	public void setEmergencey_contact(String emergencey_contact) {
		this.emergencey_contact = emergencey_contact;
	}

	
	

	public Long getId() {
		return userId;
	}

	public void setId(Long id) {
		this.userId = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Patient other = (Patient) obj;
		return Objects.equals(userId, other.getId());
	}

	
	
	

	  
}