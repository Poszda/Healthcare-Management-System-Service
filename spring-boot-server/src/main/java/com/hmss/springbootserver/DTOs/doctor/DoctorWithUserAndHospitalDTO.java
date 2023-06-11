package com.hmss.springbootserver.DTOs.doctor;

import com.hmss.springbootserver.DTOs.hospital.HospitalDTO;
import com.hmss.springbootserver.DTOs.user.UserDTO;
import com.hmss.springbootserver.DTOs.user.UserNoPasswordDTO;

import java.time.LocalTime;

public class DoctorWithUserAndHospitalDTO {
    private Long id;
    private String university;
    private String description;
    private LocalTime programStart;
    private LocalTime programEnd;
    private UserNoPasswordDTO user;
    private HospitalDTO hospital;

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

    public UserNoPasswordDTO getUser() {
        return user;
    }

    public void setUser(UserNoPasswordDTO user) {
        this.user = user;
    }

    public HospitalDTO getHospital() {
        return hospital;
    }

    public void setHospital(HospitalDTO hospital) {
        this.hospital = hospital;
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
