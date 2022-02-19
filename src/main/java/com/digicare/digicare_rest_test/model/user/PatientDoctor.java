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
    @JoinColumn(name = "patient_id")
    Patient patient;

    @ManyToOne
    @MapsId("doctorId")
    @JoinColumn(name = "doctor_id")
    Doctor doctor;

	public PatientDoctor(PatientDoctorKey id, Patient patient, Doctor doctor) {
		super();
		this.id = id;
		this.doctor = doctor;
		this.patient = patient;

	}
	
	public PatientDoctor() {}

	public PatientDoctorKey getId() {
		return id;
	}

	public void setId(PatientDoctorKey id) {
		this.id = id;
	}

	

    
    
    
}
