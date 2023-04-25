package com.hmss.springbootserver.DTOs;

import com.hmss.springbootserver.enums.UserType;

public class UserDTOTest {
    private Long id;
    private String lastName;
    private String firstName;
    private String email;
    private String password;
    private UserType userType;
    private PatientDTOTest patient;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public PatientDTOTest getPatient() {
        return patient;
    }

    public void setPatient(PatientDTOTest patient) {
        this.patient = patient;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
