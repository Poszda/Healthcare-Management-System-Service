package com.hmss.springbootserver.DTOs.appointments;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;

public class DoctorAvailableHours {
    Long doctorId;
    List<AvailableHours> dates;

    public DoctorAvailableHours() {
    }

    public DoctorAvailableHours(Long doctorId, List<AvailableHours> dates) {
        this.doctorId = doctorId;
        this.dates = dates;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public List<AvailableHours> getDates() {
        return dates;
    }

    public void setDates(List<AvailableHours> dates) {
        this.dates = dates;
    }
}
