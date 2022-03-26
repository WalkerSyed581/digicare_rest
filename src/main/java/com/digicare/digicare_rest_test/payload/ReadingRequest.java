package com.digicare.digicare_rest_test.payload;

import java.util.Date;

import com.digicare.digicare_rest_test.model.Sensor;

import org.springframework.format.annotation.DateTimeFormat;

public class ReadingRequest {
	private Long patient_id;		
	private Long sensor_id;
	private double reading;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private Date timestamp;	

	public Long getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(Long patient_id) {
		this.patient_id = patient_id;
	}
	public Long getSensor_id() {
		return sensor_id;
	}
	public void setSensor_id(Long sensor_id) {
		this.sensor_id = sensor_id;
	}
	public double getReading() {
		return reading;
	}
	public void setReading(double reading) {
		this.reading = reading;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	
}
