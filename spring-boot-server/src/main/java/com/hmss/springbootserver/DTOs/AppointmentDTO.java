package com.hmss.springbootserver.DTOs;

import com.hmss.springbootserver.entities.Patient;

public class AppointmentDTO {
    private Long id;
    private PatientDTO patient;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PatientDTO getPatient() {
        return patient;
    }

    public void setPatient(PatientDTO patient) {
        this.patient = patient;
    }
}
