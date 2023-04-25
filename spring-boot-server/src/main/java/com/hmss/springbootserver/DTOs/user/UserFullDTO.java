package com.hmss.springbootserver.DTOs.user;

import com.hmss.springbootserver.DTOs.patient.PatientSimpleDTO;
import com.hmss.springbootserver.enums.UserType;

public class UserFullDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private UserType userType;
    private PatientSimpleDTO patient;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public PatientSimpleDTO getPatient() {
        return patient;
    }

    public void setPatient(PatientSimpleDTO patient) {
        this.patient = patient;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
