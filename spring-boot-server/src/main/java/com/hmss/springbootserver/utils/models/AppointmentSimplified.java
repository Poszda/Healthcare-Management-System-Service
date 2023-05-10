package com.hmss.springbootserver.utils.models;

import java.time.LocalDate;

public class AppointmentSimplified {
    int startMinute;
    int endMinute;
    Long id;
    Long doctorId;
    LocalDate date;

    public AppointmentSimplified(int starterMinute, int endMinute, Long id, Long doctorId, LocalDate date) {
        this.startMinute = starterMinute;
        this.endMinute = endMinute;
        this.id = id;
        this.doctorId = doctorId;
        this.date = date;
    }

    public AppointmentSimplified() {
    }

    public int getStartMinute() {
        return startMinute;
    }

    public void setStartMinute(int starterMinute) {
        this.startMinute = starterMinute;
    }

    public int getEndMinute() {
        return endMinute;
    }

    public void setEndMinute(int endMinute) {
        this.endMinute = endMinute;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "AppointmentSimplified{" +
                "startMinute=" + startMinute +
                ", endMinute=" + endMinute +
                ", id=" + id +
                ", doctorId=" + doctorId +
                ", date=" + date +
                '}';
    }
}
