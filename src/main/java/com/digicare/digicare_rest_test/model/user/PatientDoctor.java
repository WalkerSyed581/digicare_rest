package com.digicare.digicare_rest_test.model.user;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class PatientDoctor {

    @EmbeddedId
    PatientDoctorKey id;

    @ManyToOne
    @MapsId("patientId")
    @JoinColumn(name = "student_id")
    Patient patient;

    @ManyToOne
    @MapsId("doctorId")
    @JoinColumn(name = "doctor_id")
    Doctor doctor;

	public PatientDoctor(PatientDoctorKey id, Patient patient, Doctor doctor) {
		super();
		this.id = id;
		this.patient = patient;
		this.doctor = doctor;
	}
	
	public PatientDoctor() {}

	public PatientDoctorKey getId() {
		return id;
	}

	public void setId(PatientDoctorKey id) {
		this.id = id;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

    
    
    
}
