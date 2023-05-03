package com.hmss.springbootserver.DTOs.doctor;

import com.hmss.springbootserver.DTOs.user.UserDTO;
import com.hmss.springbootserver.entities.User;

public class DoctorWithUserDTO {
    private Long id;
    private String university;
    private String description;
    private Long hospitalId;
    private UserDTO user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Long hospitalId) {
        this.hospitalId = hospitalId;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}