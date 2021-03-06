package com.digicare.digicare_rest_test.model;


import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Column;

import com.digicare.digicare_rest_test.model.user.*;

@Entity(name = "Assessment")
public class Assessment {
 
	
	@Id
	@GeneratedValue
	@Column(name = "id", updatable = false, nullable = false)
	protected Long id;

	@Column
  	private String notes;
	
	@Column
  	private String condition;
	
	@Column
  	private String recommendations;
	
	@Column
  	private String cg_instr;
	
	@Column
	private String data_desc;
	
	@Column
	private Date timestamp;



	

	@ManyToOne(optional = false)
	@JoinColumn(name = "patient_id", referencedColumnName = "id")
	private User patient;

	@ManyToOne(optional = false)
	@JoinColumn(name = "doctor_id", referencedColumnName = "id")
	private User doctor;


	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getRecommendations() {
		return recommendations;
	}

	public void setRecommendations(String recommendations) {
		this.recommendations = recommendations;
	}

	public String getCg_instr() {
		return cg_instr;
	}

	public void setCg_instr(String cg_instr) {
		this.cg_instr = cg_instr;
	}

	public String getData_desc() {
		return data_desc;
	}

	public void setData_desc(String data_desc) {
		this.data_desc = data_desc;
	}


	@Override
	public int hashCode() {
		return Objects.hash(doctor, id, patient);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Assessment other = (Assessment) obj;
		return Objects.equals(doctor, other.doctor) && Objects.equals(id, other.id)
				&& Objects.equals(patient, other.patient);
	}

	public Assessment( String notes, String condition, String recommendations, String cg_instr,
			String data_desc, User patient, User doctor,Date timestamp) {
		this.notes = notes;
		this.condition = condition;
		this.recommendations = recommendations;
		this.cg_instr = cg_instr;
		this.data_desc = data_desc;
		this.patient = patient;
		this.doctor = doctor;
		this.timestamp = timestamp;
	}

	public Assessment(){
		
	}

	@Override
	public String toString() {
		return "Assessment [id=" + id + ", notes=" + notes + ", condition=" + condition + ", redommendations="
				+ recommendations + ", cg_instr=" + cg_instr + ", data_desc=" + data_desc + ", patient=" + patient
				+ ", doctor=" + doctor + ", timestamp=" + timestamp + "]";
	}

	public User getPatient() {
		return patient;
	}

	public void setPatient(User patient) {
		this.patient = patient;
	}

	public User getDoctor() {
		return doctor;
	}

	public void setDoctor(User doctor) {
		this.doctor = doctor;
	}
	
	
	
	  
}
