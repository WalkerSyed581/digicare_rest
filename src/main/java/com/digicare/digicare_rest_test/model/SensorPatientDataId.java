package com.digicare.digicare_rest_test.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Embeddable;

import org.springframework.lang.NonNull;



@Embeddable
public class SensorPatientDataId implements Serializable {
 
    private static final long serialVersionUID = 1L;
    
    @NonNull
    private Long sensor_id;
    
    @NonNull
    private Long patient_id;
    
    @NonNull
	private Date timestamp;
 
    public SensorPatientDataId() {
 
    }
 
    public SensorPatientDataId(Long patient_id, Long sensor_id,Date timestamp) {
        super();
        this.sensor_id = sensor_id;
        this.patient_id = patient_id;
        this.timestamp = timestamp;
    }
 
    public Long getPatientId() {
        return patient_id;
    }
 
    public void setPatientId(Long patient_id) {
        this.patient_id = patient_id;
    }
 
    public Long getSensorId() {
        return sensor_id;
    }
 
    public void setSensorId(Long sensor_id) {
        this.sensor_id = sensor_id;
    }
 
    @Override
	public int hashCode() {
		return Objects.hash(patient_id, sensor_id, timestamp);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SensorPatientDataId other = (SensorPatientDataId) obj;
		return Objects.equals(patient_id, other.patient_id) && Objects.equals(sensor_id, other.sensor_id)
				&& Objects.equals(timestamp, other.timestamp);
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
}
