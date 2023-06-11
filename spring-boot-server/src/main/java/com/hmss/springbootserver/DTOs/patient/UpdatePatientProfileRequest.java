package com.hmss.springbootserver.DTOs.patient;

import java.time.LocalDate;

public class UpdatePatientProfileRequest {
    private String firstName;
    private String lastName ;
    private Integer height;
    private Integer weight;
    private String bloodType;
    private String phone;
    private LocalDate birthDate;

    public UpdatePatientProfileRequest() {
    }

    public UpdatePatientProfileRequest(String firstName, String lastName, Integer height, Integer weight, String bloodType, String phone, LocalDate birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.height = height;
        this.weight = weight;
        this.bloodType = bloodType;
        this.phone = phone;
        this.birthDate = birthDate;
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

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
