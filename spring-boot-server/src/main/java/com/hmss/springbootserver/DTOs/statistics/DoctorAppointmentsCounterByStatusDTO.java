package com.hmss.springbootserver.DTOs.statistics;

public class DoctorAppointmentsCounterByStatusDTO {
    String status;
    int counter;

    public DoctorAppointmentsCounterByStatusDTO(String status, int counter) {
        this.status = status;
        this.counter = counter;
    }

    public DoctorAppointmentsCounterByStatusDTO() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
