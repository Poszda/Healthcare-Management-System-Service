package com.hmss.springbootserver.utils.models.projections;

import com.hmss.springbootserver.entities.Procedure;

import java.time.LocalDateTime;

public interface DoctorAppointmentProjection {
    Long getId();
    LocalDateTime getDate();
    String getProcedureName();
    Integer getDuration();
    Long getPatientId();
    String getPhone();
    Integer getAge();
    String getFirstName();
    String getLastName();
    Integer getDiagnosticId();
}
