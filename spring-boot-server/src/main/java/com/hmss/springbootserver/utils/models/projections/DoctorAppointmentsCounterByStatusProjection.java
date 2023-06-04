package com.hmss.springbootserver.utils.models.projections;

public interface DoctorAppointmentsCounterByStatusProjection {
    String getStatus();
    Integer getCounter();
}
