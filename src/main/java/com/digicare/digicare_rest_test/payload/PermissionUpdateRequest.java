package com.digicare.digicare_rest_test.payload;


public class PermissionUpdateRequest {
	private Long patient_id;		
	private Long doctor_id_old;

	private Long doctor_id;

	public Long getPatient_id() {
		return patient_id;
	}

	public void setPatient_id(Long patient_id) {
		this.patient_id = patient_id;
	}

	public Long getDoctor_id_old() {
		return doctor_id_old;
	}

	public void setDoctor_id_old(Long doctor_id_old) {
		this.doctor_id_old = doctor_id_old;
	}

	public Long getDoctor_id() {
		return doctor_id;
	}

	public void setDoctor_id(Long doctor_id) {
		this.doctor_id = doctor_id;
	}

	

}