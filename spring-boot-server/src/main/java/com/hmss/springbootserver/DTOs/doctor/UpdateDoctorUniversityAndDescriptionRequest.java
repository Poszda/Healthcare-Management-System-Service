package com.hmss.springbootserver.DTOs.doctor;

public class UpdateDoctorUniversityAndDescriptionRequest {
    private String university;
    private String description;

    public UpdateDoctorUniversityAndDescriptionRequest(String university, String description) {
        this.university = university;
        this.description = description;
    }

    public UpdateDoctorUniversityAndDescriptionRequest() {
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
}
