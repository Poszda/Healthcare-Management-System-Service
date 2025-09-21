package com.hmss.springbootserver.DTOs.doctor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

public class CreateDoctorRequestDTO {
    @NotBlank(message = "First name is required.")
    private String firstName;
    @NotBlank(message = "Last name is required.")
    private String lastName;
    @NotBlank(message = "Email is required.")
    @Email(message = "Email must be a valid format.")
    private String email;
    @NotBlank(message = "Password is required.")
    private String password;
    @NotNull(message = "Speciality ID is required.")
    private Long specialityId;
    @NotNull(message = "Program start time is required.")
    private LocalTime programStart;
    @NotNull(message = "Program end time is required.")
    private LocalTime programEnd;
    @NotNull(message = "Hospital ID is required.")
    private Long hospitalId ;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getSpecialityId() {
        return specialityId;
    }

    public void setSpecialityId(Long specialityId) {
        this.specialityId = specialityId;
    }

    public Long getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Long hospitalId) {
        this.hospitalId = hospitalId;
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
