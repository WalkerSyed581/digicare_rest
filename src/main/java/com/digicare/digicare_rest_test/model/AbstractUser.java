package com.digicare.digicare_rest_test.model;

import java.util.Objects;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.MappedSuperclass;
import javax.persistence.GeneratedValue;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public abstract class AbstractUser implements Serializable {

  @Id
  @GeneratedValue
  @Column(name = "id", updatable = false, nullable = false)
  protected Long id;

  @Column(table="USER_TABLE")
  private String firstName;

  @Column(table="USER_TABLE")
  private String lastName;

  @Column(table="USER_TABLE")
  private boolean active;
  
  @Column(table="USER_TABLE")
  private String email;

  @Column(table="USER_TABLE")
  private String password;

  @Column(length = 11,table="USER_TABLE")
  private String phone_no;
  
  @Temporal(TemporalType.DATE)
  @Column(table="USER_TABLE")
  private Date dob;

  @Enumerated(EnumType.STRING)
  @Column(table="USER_TABLE")
  private Gender gender;

  @Column(table="USER_TABLE")
  private String address;

  @Column(length = 13,table="USER_TABLE")
  private String cnic;

  @Column(table="USER_TABLE")
  private int age;

  @Column(table="USER_TABLE")
  private String roles;



  public boolean isActive() {
	return active;
}

public void setActive(boolean active) {
	this.active = active;
}

public String getRoles() {
	return roles;
  }
  
  public void setRoles(String roles) {
	this.roles = roles;
  }

public String getAddress() {
    return address;
  }

  public int getAge() {
    return age;
  }
  public String getCnic() {
    return cnic;
  }
  public Date getDob() {
    return dob;
  }
  public String getEmail() {
    return email;
  }
  public String getFirstName() {
    return firstName;
  }
  public Gender getGender() {
    return gender;
  }
  public String getLastName() {
    return lastName;
  }
  public String getPassword() {
    return password;
  }
  public String getPhone_no() {
    return phone_no;
  }
  public Long getId() {
    return this.id;
  }

  public String getName() {
    return this.firstName + this.lastName;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setName(String name) {
    String[] parts = name.split(" ");
    this.firstName = parts[0];
    this.lastName = parts[1];
  }

  public void setAddress(String address) {
    this.address = address;
  }
  public void setAge(int age) {
    this.age = age;
  }
  public void setCnic(String cnic) {
    this.cnic = cnic;
  }
  public void setDob(Date dob) {
    this.dob = dob;
  }
  public void setEmail(String email) {
    this.email = email;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
  public void setGender(Gender gender) {
    this.gender = gender;
  }
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public void setPhone_no(String phone_no) {
    this.phone_no = phone_no;
  }

@Override
public int hashCode() {
	return Objects.hash(cnic, email, id, phone_no);
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	User other = (User) obj;
	return Objects.equals(cnic, other.getCnic()) && Objects.equals(email, other.getEmail()) && Objects.equals(id, other.id)
			&& Objects.equals(phone_no, other.getPhone_no());
}

@Override
public String toString() {
	return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", password="
			+ password + ", phone_no=" + phone_no + ", dob=" + dob + ", gender=" + gender + ", address=" + address
			+ ", cnic=" + cnic + ", age=" + age + "]";
}
  
  
  
}