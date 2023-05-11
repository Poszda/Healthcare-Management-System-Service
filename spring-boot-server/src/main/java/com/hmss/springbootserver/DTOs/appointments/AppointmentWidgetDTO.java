package com.hmss.springbootserver.DTOs.appointments;

import com.hmss.springbootserver.entities.Diagnostic;
import com.hmss.springbootserver.entities.Doctor;
import com.hmss.springbootserver.entities.Patient;
import com.hmss.springbootserver.entities.Procedure;
import jakarta.persistence.*;

import java.time.LocalDateTime;

public class AppointmentWidgetDTO {
    private Long id;
    private LocalDateTime date;
    private Doctor doctor;
    private Procedure procedure;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Procedure getProcedure() {
        return procedure;
    }

    public void setProcedure(Procedure procedure) {
        this.procedure = procedure;
    }

}
