package com.hmss.springbootserver.utils.models.projections;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface AppointmentStatisticProjection {
    String getMonth();
    Integer getAppointments();
    Integer getTotal();
}
