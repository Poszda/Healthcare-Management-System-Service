package com.hmss.springbootserver.DTOs;

import java.time.LocalDateTime;
import java.util.List;

public class AvailableAppointmentsRequestDTO {
    List<Long> doctorIds;
    LocalDateTime startDate;
    LocalDateTime endDate;

    public List<Long> getDoctorIds() {
        return doctorIds;
    }

    public void setDoctorIds(List<Long> doctorIds) {
        this.doctorIds = doctorIds;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
}
