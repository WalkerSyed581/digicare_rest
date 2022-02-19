package com.digicare.digicare_rest_test.model.user;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;

@Entity(name = "caregiver")
public class Caregiver {

	@Id
	@GeneratedValue
    @Column(name = "id")
    private long id;

	
	
 
	
	@Column(length = 11)
  	private String relationship;
	
	
	@OneToOne(optional = false)
	@JoinColumn(name = "patient_id", referencedColumnName = "id")
	private User patient;


	public Caregiver() {
		// TODO Auto-generated constructor stub
	}


	public Caregiver(String relationship,User patient) {
		this.relationship = relationship;
		this.patient = patient;
	}

	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	



	public String getRelationship() {
		return relationship;
	}


	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}


	public User getPatient() {
		return patient;
	}


	public void setPatient(User patient) {
		this.patient = patient;
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
		return "Caregiver [id=" + id +  ", relationship=" + relationship + ", patient=" + patient
				+ "]";
	}

	
	
	
	
	

	  
}