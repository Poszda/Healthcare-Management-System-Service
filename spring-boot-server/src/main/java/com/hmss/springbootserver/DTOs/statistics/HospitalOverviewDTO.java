package com.hmss.springbootserver.DTOs.statistics;

public class HospitalOverviewDTO {
    private Long appointments;
    private Double estimatedEarnings;
    private Float averagePatientAge;

    public HospitalOverviewDTO(Long appointments, Double estimatedEarnings, Float averagePatientAge) {
        this.appointments = appointments;
        this.estimatedEarnings = estimatedEarnings;
        this.averagePatientAge = averagePatientAge;
    }

    public HospitalOverviewDTO() {
    }

    public Long getAppointments() {
        return appointments;
    }

    public void setAppointments(Long appointments) {
        this.appointments = appointments;
    }

    public Double getEstimatedEarnings() {
        return estimatedEarnings;
    }

    public void setEstimatedEarnings(Double estimatedEarnings) {
        this.estimatedEarnings = estimatedEarnings;
    }

    public Float getAveragePatientAge() {
        return averagePatientAge;
    }

    public void setAveragePatientAge(Float averagePatientAge) {
        this.averagePatientAge = averagePatientAge;
    }
}
