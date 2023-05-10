package com.hmss.springbootserver.DTOs.appointments;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class AvailableHours {
    LocalDate date;
    List<LocalTime> hours;

    public AvailableHours(LocalDate date, List<LocalTime> hours) {
        this.date = date;
        this.hours = hours;
    }

    public AvailableHours() {
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<LocalTime> getHours() {
        return hours;
    }

    public void setHours(List<LocalTime> hours) {
        this.hours = hours;
    }
}
