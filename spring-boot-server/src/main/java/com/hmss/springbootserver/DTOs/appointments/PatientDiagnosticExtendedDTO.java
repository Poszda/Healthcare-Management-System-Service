package com.hmss.springbootserver.DTOs.appointments;

import com.hmss.springbootserver.entities.Appointment;
import com.hmss.springbootserver.entities.Medication;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PatientDiagnosticExtendedDTO {
    private Long id;
    private String diagnostic;
    private LocalDate createdAt;
    private Long doctorId;
    private String doctorFirstName;
    private String doctorLastName;
    private String description;
    private LocalDateTime date;
    private String procedure;
    private String speciality;
    private String hospital;
    private List<MedicationDTO> medications = new ArrayList<>();


    public PatientDiagnosticExtendedDTO() {
    }

    public PatientDiagnosticExtendedDTO(Long id, String diagnostic, LocalDate createdAt, String doctorFirstName, String doctorLastName, String description, LocalDateTime date, String procedure, String speciality, String hospital, List<MedicationDTO> medications) {
        this.id = id;
        this.diagnostic = diagnostic;
        this.createdAt = createdAt;
        this.doctorFirstName = doctorFirstName;
        this.doctorLastName = doctorLastName;
        this.description = description;
        this.date = date;
        this.procedure = procedure;
        this.speciality = speciality;
        this.hospital = hospital;
        this.medications = medications;
    }

    public PatientDiagnosticExtendedDTO(Long id, String diagnostic, LocalDate createdAt, String doctorFirstName, String doctorLastName, Long doctorId, String description, LocalDateTime date, String procedure, String speciality, String hospital){
        this.id = id;
        this.diagnostic = diagnostic;
        this.createdAt = createdAt;
        this.doctorFirstName = doctorFirstName;
        this.doctorLastName = doctorLastName;
        this.doctorId = doctorId;
        this.description = description;
        this.date = date;
        this.procedure = procedure;
        this.speciality = speciality;
        this.hospital = hospital;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDoctorFirstName() {
        return doctorFirstName;
    }

    public void setDoctorFirstName(String doctorFirstName) {
        this.doctorFirstName = doctorFirstName;
    }

    public String getDoctorLastName() {
        return doctorLastName;
    }

    public void setDoctorLastName(String doctorLastName) {
        this.doctorLastName = doctorLastName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getProcedure() {
        return procedure;
    }

    public void setProcedure(String procedure) {
        this.procedure = procedure;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public List<MedicationDTO> getMedications() {
        return medications;
    }

    public void setMedications(List<MedicationDTO> medications) {
        this.medications = medications;
    }

    public String getDiagnostic() {
        return diagnostic;
    }

    public void setDiagnostic(String diagnostic) {
        this.diagnostic = diagnostic;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }
}
