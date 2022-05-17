package com.digicare.digicare_rest_test.payload;

import java.util.Date;

public class AssessmentRequest {	
  	private String notes;
	
  	private String condition;
	
  	private String recommendations;
	
  	private String cg_instr;
	
	private String data_desc;

	private long patient_id;

	private Date timestamp;

	

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getRecommendations() {
		return recommendations;
	}

	public void setRecommendations(String recommendations) {
		this.recommendations = recommendations;
	}

	public String getCg_instr() {
		return cg_instr;
	}

	public void setCg_instr(String cg_instr) {
		this.cg_instr = cg_instr;
	}

	public String getData_desc() {
		return data_desc;
	}

	public void setData_desc(String data_desc) {
		this.data_desc = data_desc;
	}

	public long getPatient_id() {
		return patient_id;
	}

	public void setPatient_id(long patient_id) {
		this.patient_id = patient_id;
	}

	
}
