package com.hmss.springbootserver.DTOs.appointments;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public class CreateAppointmentRequestDTO {
    @NotNull(message = "Date is required.")
    private LocalDate date;
    @NotNull(message = "Time is required.")
    private LocalTime time;
    @NotNull(message = "Procedure ID is required.")
    private Long procedureId;
    @NotNull(message = "Doctor ID is required.")
    private Long doctorId;
    @NotNull(message = "Patient ID is required.")
    private Long patientId;


    public CreateAppointmentRequestDTO(LocalDate date, LocalTime time, Long procedureId, Long doctorId, Long patientId) {
        this.date = date;
        this.time = time;
        this.procedureId = procedureId;
        this.doctorId = doctorId;
        this.patientId = patientId;
    }

    public CreateAppointmentRequestDTO() {
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Long getProcedureId() {
        return procedureId;
    }

    public void setProcedureId(Long procedureId) {
        this.procedureId = procedureId;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    @Override
    public String toString() {
        return "CreateAppointmentRequestDTO{" +
                "date=" + date +
                ", time=" + time +
                ", procedureId=" + procedureId +
                ", doctorId=" + doctorId +
                ", patientId=" + patientId +
                '}';
    }
}
