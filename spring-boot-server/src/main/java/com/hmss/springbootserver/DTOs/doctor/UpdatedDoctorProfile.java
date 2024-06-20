package com.hmss.springbootserver.DTOs.doctor;

public class UpdatedDoctorProfile {
    private String university;
    private String description;
    private String profileImage;

    public UpdatedDoctorProfile(String university, String description, String profileImage) {
        this.university = university;
        this.description = description;
        this.profileImage = profileImage;
    }

    public UpdatedDoctorProfile() {
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

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
}
