package com.hmss.springbootserver.DTOs.doctor;

import com.hmss.springbootserver.DTOs.user.UserNoPasswordDTO;

import java.time.LocalTime;

public class DoctorWithUserDTO {
    private Long id;
    private String university;
    private String description;
    private LocalTime programStart;
    private LocalTime programEnd;
    private Long hospitalId;
    private UserNoPasswordDTO user;

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

    public UserNoPasswordDTO getUser() {
        return user;
    }

    public void setUser(UserNoPasswordDTO user) {
        this.user = user;
    }

    public LocalTime getProgramStart() {
        return programStart;
    }

    public void setProgramStart(LocalTime programStart) {
        this.programStart = programStart;
    }

    public LocalTime getProgramEnd() {
        return programEnd;
    }

    public void setProgramEnd(LocalTime programEnd) {
        this.programEnd = programEnd;
    }
}
