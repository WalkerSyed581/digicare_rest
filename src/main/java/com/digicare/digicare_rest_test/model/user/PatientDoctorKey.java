package com.digicare.digicare_rest_test.model.user;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PatientDoctorKey implements Serializable {

    @Column(name = "patient_id")
    Long patientId;

    @Column(name = "doctor_id")
    Long doctorId;
    
    public PatientDoctorKey() {
    	
    }

	public PatientDoctorKey(Long patientId, Long doctorId) {
		super();
		this.patientId = patientId;
		this.doctorId = doctorId;
	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public Long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(doctorId, patientId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PatientDoctorKey other = (PatientDoctorKey) obj;
		return Objects.equals(doctorId, other.doctorId) && Objects.equals(patientId, other.patientId);
	}

    
}
