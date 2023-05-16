package com.hmss.springbootserver.utils.models.projections;

import java.time.LocalDateTime;

public interface AppointmentCardProjection {
    Long getId();
    LocalDateTime getDateTime();
    Long getDiagnosticId();
    String getDoctorFirstName();
    String getDoctorLastName();
    String getProcedureName();
    String getHospitalName();
    float getPrice();
    int getDuration();
    String getDoctorSpeciality();
}
