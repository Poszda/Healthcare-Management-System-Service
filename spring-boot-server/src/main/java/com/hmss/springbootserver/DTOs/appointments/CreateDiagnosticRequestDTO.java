package com.hmss.springbootserver.DTOs.appointments;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public class CreateDiagnosticRequestDTO {
    private Long appointmentId;
    @NotBlank(message = "Diagnostic is required.")
    private String diagnostic;
    private String description;
    private List<MedicationDTO> medications;

    public Long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getDiagnostic() {
        return diagnostic;
    }

    public void setDiagnostic(String diagnostic) {
        this.diagnostic = diagnostic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<MedicationDTO> getMedications() {
        return medications;
    }

    public void setMedications(List<MedicationDTO> medications) {
        this.medications = medications;
    }
}
