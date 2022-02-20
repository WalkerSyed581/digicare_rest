package com.digicare.digicare_rest_test.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.util.Objects;

import javax.persistence.Column;


@Entity(name = "Sensor")
public class Sensor {
 
	
	@Id
	@GeneratedValue
	@Column(name = "id", updatable = false, nullable = false)
	protected Long id;
	
	

	@Column
  	private String name;
	
	@Column
	private String data_desc;

	public Sensor(String name, String data_desc) {
		this.name = name;
		this.data_desc = data_desc;
	}

	public Sensor() {
		super();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getData_desc() {
		return data_desc;
	}

	public void setData_desc(String data_desc) {
		this.data_desc = data_desc;
	}

	

	@Override
	public String toString() {
		return "Sensor [id=" + id + ", name=" + name + ", data_desc=" + data_desc + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sensor other = (Sensor) obj;
		return Objects.equals(id, other.id);
	}
	
	
	  
}