package com.hmss.springbootserver.DTOs.doctor;

import com.hmss.springbootserver.DTOs.hospital.HospitalDTO;
import com.hmss.springbootserver.DTOs.speciality.SpecialityDTO;
import com.hmss.springbootserver.DTOs.user.UserDTO;

import java.time.LocalTime;

public class DoctorProfileDTO {
    private Long id;
    private String university;
    private String description;
    private LocalTime programStart;
    private LocalTime programEnd;
    private UserDTO user;
    private HospitalDTO hospital;
    private SpecialityDTO speciality;
    private String profileImage;

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

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public HospitalDTO getHospital() {
        return hospital;
    }

    public void setHospital(HospitalDTO hospital) {
        this.hospital = hospital;
    }

    public SpecialityDTO getSpeciality() {
        return speciality;
    }

    public void setSpeciality(SpecialityDTO speciality) {
        this.speciality = speciality;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
}
