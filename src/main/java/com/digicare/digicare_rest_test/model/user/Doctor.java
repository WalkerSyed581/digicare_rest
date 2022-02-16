package com.digicare.digicare_rest_test.model.user;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.CascadeType;
import javax.persistence.Column;

@Entity(name = "doctor")
public class Doctor {
	
	@Id
	@GeneratedValue
    @Column(name = "id")
    private long id;

	@OneToOne
    @JoinColumn(name = "user_id",unique=true)
    private User user;

	@Column(length = 11)
  	private String emergencey_contact;
	
	@OneToMany(mappedBy = "doctor")
    Set<PatientDoctor> permission;
	
	public Doctor() {
		// TODO Auto-generated constructor stub
	}

	public Doctor(User user,String emergencey_contact) {
		this.user = user;
		this.emergencey_contact = emergencey_contact;
	}

	public String getEmergencey_contact() {
		return emergencey_contact;
	}

	public void setEmergencey_contact(String emergencey_contact) {
		this.emergencey_contact = emergencey_contact;
	}

	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
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
		return Objects.equals(id, other.getId());
	}

	
	
	@Override
	public String toString() {
		return "Patient [id=" + id + ", user=" + user + ", emergencey_contact=" + emergencey_contact + "]";
	}

	  
}