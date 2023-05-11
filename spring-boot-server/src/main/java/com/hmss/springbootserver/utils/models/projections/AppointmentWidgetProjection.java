package com.hmss.springbootserver.utils.models.projections;

import java.time.LocalDateTime;

public interface AppointmentWidgetProjection {
    Long getId();
    LocalDateTime getDate();
    String getFirstName();
    String getLastName();
    String getProcedureName();
    float getPrice();
    int getDuration();
    String getDoctorSpeciality();
}
