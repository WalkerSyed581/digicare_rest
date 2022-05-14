package com.digicare.digicare_rest_test.payload;


public class PermissionRequest {
	private Long patientId;		
	private Long doctorId;


	
	

	public Long getpatientId() {
		return patientId;
	}
	public void setpatientId(Long patientId) {
		this.patientId = patientId;
	}
	public Long getdoctorId() {
		return doctorId;
	}
	public void setdoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}
	
	@Override
	public String toString() {
		return "PermissionRequest [doctorId=" + doctorId + ", patientId=" + patientId + "]";
	}

	
}
