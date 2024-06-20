package com.hmss.springbootserver.DTOs.doctor;

import com.hmss.springbootserver.utils.models.projections.DoctorSuggestionInfoProjection;

public class DoctorSuggestionInfoDTO implements DoctorSuggestionInfoProjection {

    Long id;
    String firstName;
    String lastName;
    String hospital;
    String speciality;
    String profileImage;

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public String getFirstName() {
        return this.firstName;
    }

    @Override
    public String getLastName() {
        return this.lastName;
    }

    @Override
    public String getSpeciality() {
        return this.speciality;
    }

    @Override
    public String getHospital() {
        return this.hospital;
    }

    @Override
    public String getProfileImage() {
        return this.profileImage;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
}
