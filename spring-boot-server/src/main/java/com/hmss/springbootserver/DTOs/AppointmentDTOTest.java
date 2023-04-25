package com.hmss.springbootserver.DTOs;

public class AppointmentDTOTest {
    private Long id;
    private PatientDTOTest patient;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PatientDTOTest getPatient() {
        return patient;
    }

    public void setPatient(PatientDTOTest patient) {
        this.patient = patient;
    }
}
