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

	
	@OneToOne
    @JoinColumn(name = "user_id",unique=true)
    private User user;
 
	
	@Column(length = 11)
  	private String relationship;
	
	
	@OneToOne(optional = false)
	@JoinColumn(name = "patient_id", referencedColumnName = "id")
	private Patient patient;


	public Caregiver() {
		// TODO Auto-generated constructor stub
	}


	public Caregiver(User user,String relationship,Patient patient) {
		this.user = user;
		this.relationship = relationship;
		this.patient = patient;
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
		return "Caregiver [id=" + id + ", user=" + user + ", relationship=" + relationship + ", patient=" + patient
				+ "]";
	}

	
	
	
	
	

	  
}