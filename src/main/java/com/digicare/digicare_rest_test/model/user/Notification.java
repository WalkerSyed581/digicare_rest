package com.digicare.digicare_rest_test.model.user;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "notification")
public class Notification {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "title")
	private String title;

	@Column(name = "content")
	private String content;

	@ManyToOne
    @JoinColumn(name="patient_id", nullable=false)
	private User patient;	

	@Column
	private Date timestamp;

	
	public Notification() {
		
	}

	public Notification(String title, String content,User patient,Date timestamp) {
		this.title = title;
		this.content = content;
		this.patient = patient;
		this.timestamp = timestamp;
	}

	public Long getId() {
		return id;
	}

	

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getPatient() {
		return patient;
	}

	public void setPatient(User patient) {
		this.patient = patient;
	}

	

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "Notification [content=" + content + ", id=" + id + ", patient=" + patient + ", timestamp=" + timestamp
				+ ", title=" + title + "]";
	}


	
	

	
}
