package com.hmss.springbootserver.utils.models.projections;

import com.hmss.springbootserver.entities.Procedure;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface DoctorAppointmentProjection {
    Long getId();
    LocalDateTime getDate();
    String getProcedureName();
    Integer getDuration();
    Long getPatientId();
    String getPhone();
    LocalDate getBirthDate();
    String getFirstName();
    String getLastName();
    Integer getDiagnosticId();
    String getProfileImage();
}
