package com.hmss.springbootserver.DTOs.patient;

import com.hmss.springbootserver.DTOs.user.UserNoPasswordDTO;
import com.hmss.springbootserver.DTOs.user.UserWithFileMetadataDTO;

import java.time.LocalDate;

public class PatientWithUserWithFileMetadataDTO {
    private Long id;
    private String phone;
    private LocalDate birthDate;
    private String bloodType;
    private Integer height;
    private Integer weight;
    private UserWithFileMetadataDTO user;

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

    public UserWithFileMetadataDTO getUser() {
        return user;
    }

    public void setUser(UserWithFileMetadataDTO user) {
        this.user = user;
    }
}
