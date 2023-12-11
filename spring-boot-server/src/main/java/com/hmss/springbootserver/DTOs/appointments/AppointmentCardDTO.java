package com.hmss.springbootserver.DTOs.appointments;

import java.time.LocalDateTime;

public class AppointmentCardDTO {
    Long Id;
    LocalDateTime DateTime;
    Long DiagnosticId;
    Long DoctorId;
    String DoctorFirstName;
    String DoctorLastName;
    String ProcedureName;
    String HospitalName;
    float Price;
    int Duration;
    String DoctorSpeciality;
    String ProfileImage;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public LocalDateTime getDateTime() {
        return DateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        DateTime = dateTime;
    }

    public Long getDiagnosticId() {
        return DiagnosticId;
    }

    public void setDiagnosticId(Long diagnosticId) {
        DiagnosticId = diagnosticId;
    }

    public Long getDoctorId() {
        return DoctorId;
    }

    public void setDoctorId(Long doctorId) {
        DoctorId = doctorId;
    }

    public String getDoctorFirstName() {
        return DoctorFirstName;
    }

    public void setDoctorFirstName(String doctorFirstName) {
        DoctorFirstName = doctorFirstName;
    }

    public String getDoctorLastName() {
        return DoctorLastName;
    }

    public void setDoctorLastName(String doctorLastName) {
        DoctorLastName = doctorLastName;
    }

    public String getProcedureName() {
        return ProcedureName;
    }

    public void setProcedureName(String procedureName) {
        ProcedureName = procedureName;
    }

    public String getHospitalName() {
        return HospitalName;
    }

    public void setHospitalName(String hospitalName) {
        HospitalName = hospitalName;
    }

    public float getPrice() {
        return Price;
    }

    public void setPrice(float price) {
        Price = price;
    }

    public int getDuration() {
        return Duration;
    }

    public void setDuration(int duration) {
        Duration = duration;
    }

    public String getDoctorSpeciality() {
        return DoctorSpeciality;
    }

    public void setDoctorSpeciality(String doctorSpeciality) {
        DoctorSpeciality = doctorSpeciality;
    }

    public String getProfileImage() {
        return ProfileImage;
    }

    public void setProfileImage(String profileImage) {
        ProfileImage = profileImage;
    }
}
