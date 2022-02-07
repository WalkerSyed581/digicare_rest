package com.digicare.digicare_rest_test.model.user;

import java.util.Objects;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.MappedSuperclass;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.digicare.digicare_rest_test.model.role.Role;

@Entity
@Table(name = "user")
public class User {

  @Id
  @GeneratedValue
  @Column(name = "id", updatable = false, nullable = false)
  protected Long id;

  @Column
  private String firstName;

  @Column
  private String lastName;

  @Column
  private boolean active;
  
  @Column(unique=true)
  private String email;

  @Column
  private String password;

  @Column(length = 11)
  private String phone_no;
  
  @Temporal(TemporalType.DATE)
  @Column
  private Date dob;

  @Enumerated(EnumType.STRING)
  @Column
  private Gender gender;


  @Column(length = 13)
  private String cnic;

  @Column
  private int age;

  
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
  private List<Role> roles;

  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "address_id")
  private Address address;

  
  
  public User(Long id, String firstName, String lastName, boolean active, String email, String password, String phone_no,
		Date dob, Gender gender, String cnic, int age, List<Role> roles, Address address) {
	super();
	this.id = id;
	this.firstName = firstName;
	this.lastName = lastName;
	this.active = active;
	this.email = email;
	this.password = password;
	this.phone_no = phone_no;
	this.dob = dob;
	this.gender = gender;
	this.cnic = cnic;
	this.age = age;
	this.roles = roles;
	this.address = address;
  }

public boolean isActive() {
	return active;
  }

  public void setActive(boolean active) {
	this.active = active;
  }
	
  public List<Role> getRoles() {
	return roles == null ? null : new ArrayList<>(roles);
  }

  public void setRoles(List<Role> roles) {
	if (roles == null) {
		this.roles = null;
	} else {
		this.roles = Collections.unmodifiableList(roles);
	}
  }

  public Address getAddress() {
	return address;
  }

  public void setAddress(Address address) {
	this.address = address;
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