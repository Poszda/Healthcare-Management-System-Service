package com.hmss.springbootserver.DTOs.patient;

import com.hmss.springbootserver.DTOs.user.UserNoPasswordDTO;

import java.time.LocalDate;

public class PatientWithUserDTO {
    private Long id;
    private String phone;
    private LocalDate birthDate;
    private String bloodType;
    private Integer height;
    private Integer weight;
    private UserNoPasswordDTO user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
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

    public UserNoPasswordDTO getUser() {
        return user;
    }

    public void setUser(UserNoPasswordDTO user) {
        this.user = user;
    }
}
