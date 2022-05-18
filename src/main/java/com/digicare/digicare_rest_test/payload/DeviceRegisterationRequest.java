package com.digicare.digicare_rest_test.payload;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

public class DeviceRegisterationRequest {
	private Long patient_id;		
	private String public_key;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private Date created_at;



	public Long getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(Long patient_id) {
		this.patient_id = patient_id;
	}
	public String getPublic_key() {
		return public_key;
	}
	public void setPublic_key(String public_key) {
		this.public_key = public_key;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	
	
}
