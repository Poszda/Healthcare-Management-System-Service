package com.hmss.springbootserver.utils.models.projections;

import java.time.LocalDateTime;

public interface AppointmentCard {
    Long getId();
    LocalDateTime getDate();
    Long getDiagnosticId();
    String getFirstName();
    String getLastName();
    String getProcedureName();
    float getPrice();
    int getDuration();
    String getDoctorSpeciality();
}
