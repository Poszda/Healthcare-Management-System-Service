package com.hmss.springbootserver.DTOs.user;

import com.hmss.springbootserver.DTOs.patient.PatientDTO;
import com.hmss.springbootserver.enums.UserType;

public class PatientLoginDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private UserType userType;
    private String jwtToken;
    private PatientDTO patient;

    public PatientLoginDTO() {
    }

    public PatientLoginDTO(Long id, String firstName, String lastName, String email, UserType userType, String jwtToken, PatientDTO patient) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userType = userType;
        this.jwtToken = jwtToken;
        this.patient = patient;
    }

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

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public PatientDTO getPatient() {
        return patient;
    }

    public void setPatient(PatientDTO patient) {
        this.patient = patient;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
