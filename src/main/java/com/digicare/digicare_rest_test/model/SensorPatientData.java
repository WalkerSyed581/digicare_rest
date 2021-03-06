package com.digicare.digicare_rest_test.model;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;

import com.digicare.digicare_rest_test.model.user.User;

// import org.apache.logging.log4j.message.TimestampMessage;

import javax.persistence.ManyToOne;
// import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;



@Entity(name = "SensorPatientData")
public class SensorPatientData {
	
	@Id
	@GeneratedValue
	@Column(name = "id", updatable = false, nullable = false)
	protected Long id;
	
//	@EmbeddedId
//    private SensorPatientDataId id = new SensorPatientDataId();
 
	@Column
	private double reading;
	
	@ManyToOne
    @JoinColumn(name="patient_id", nullable=false)
	private User patient;	
	
	@ManyToOne
    @JoinColumn(name="sensor_id", nullable=false)
	private Sensor sensor;
	
	@Column
	private Date timestamp;



	public double getReading() {
		return reading;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public void setReading(double reading) {
		this.reading = reading;
	}

	public User getPatient() {
		return patient;	
	}

	public void setPatient(User patient) {
		this.patient = patient;
	}

	public Sensor getSensor() {
		return sensor;
	}

	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}
	
	public SensorPatientData() {
		super();
	}

	public SensorPatientData(Date timestamp, double reading, User patient, Sensor sensor) {
		super();
		this.reading = reading;
		this.patient = patient;
		this.sensor = sensor;
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "SensorUserData [timestamp= " + this.getTimestamp() + ", reading=" + reading + ", patient=" + patient + ", sensor="
				+ sensor + "]";
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
		SensorPatientData other = (SensorPatientData) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
}
