package com.digicare.digicare_rest_test.payload;

import com.digicare.digicare_rest_test.model.user.*;
import java.util.Date;

public class SignUpRequest {
    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String phone_no;

    private Date dob;

    private Address address;

    private Gender gender;

    private String cnic;

    private int age;

    private int role;
    
    private String emergencey_contact;

    private long cg_user;

    private String cg_relationship;

    public String getEmergencey_contact() {
        return emergencey_contact;
    }

    public void setEmergencey_contact(String emergencey_contact) {
        this.emergencey_contact = emergencey_contact;
    }

    public long getCg_user() {
        return cg_user;
    }

    public void setCg_user(long cg_user) {
        this.cg_user = cg_user;
    }

    public String getCg_relationship() {
        return cg_relationship;
    }

    public void setCg_relationship(String cg_relationship) {
        this.cg_relationship = cg_relationship;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

   

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getCnic() {
        return cnic;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }


    
}
