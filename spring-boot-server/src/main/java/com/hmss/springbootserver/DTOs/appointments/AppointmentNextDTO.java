package com.hmss.springbootserver.DTOs.appointments;

import java.time.LocalDateTime;

public class AppointmentNextDTO {
    String FirstName;
    String LastName;
    LocalDateTime DateTime;
    int Duration;
    Long OtherId;
    Long Id;
    String ProfileImage;

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public LocalDateTime getDateTime() {
        return DateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        DateTime = dateTime;
    }

    public int getDuration() {
        return Duration;
    }

    public void setDuration(int duration) {
        Duration = duration;
    }

    public Long getOtherId() {
        return OtherId;
    }

    public void setOtherId(Long otherId) {
        OtherId = otherId;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getProfileImage() {
        return ProfileImage;
    }

    public void setProfileImage(String profileImage) {
        ProfileImage = profileImage;
    }
}
