package com.hmss.springbootserver.utils.models.projections;

public interface AppointmentStatisticProjection {
    String getMonth();
    Integer getAppointments();
    Integer getTotal();
}
