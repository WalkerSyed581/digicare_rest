package com.digicare.digicare_rest_test.model.user;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;

// import org.apache.logging.log4j.message.TimestampMessage;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
// import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

@Entity(name = "DeviceRegisteration")
public class DeviceRegisteration {

	@Id
	@GeneratedValue
	@Column(name = "id", updatable = false, nullable = false)
	protected Long id;
	
	@Column(unique=true,nullable=false)
	private String publicKey;
	
	@OneToOne
    @JoinColumn(name="patient_id", nullable=false)
	private User patient;

	@Column
	private Date created_at;



	public DeviceRegisteration(Long id, String publicKey, User patient, Date created_at) {
		this.id = id;
		this.publicKey = publicKey;
		this.patient = patient;
		this.created_at = created_at;
	}

	@Override
	public String toString() {
		return "DeviceRegisteration [created_at=" + created_at + ", id=" + id + ", patient=" + patient + ", publicKey="
				+ publicKey + "]";
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPublic_key() {
		return publicKey;
	}

	public void setPublic_key(String publicKey) {
		this.publicKey = publicKey;
	}

	public User getPatient() {
		return patient;
	}

	public void setPatient(User patient) {
		this.patient = patient;
	}	
	

	
	
}
