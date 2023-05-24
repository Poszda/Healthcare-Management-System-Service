package com.hmss.springbootserver.DTOs.statistics;

public class HospitalOverview {
    private Long appointments;
    private Long estimatedEarnings;
    private Double averagePatientAge;

    public Long getAppointments() {
        return appointments;
    }

    public void setAppointments(Long appointments) {
        this.appointments = appointments;
    }

    public Long getEstimatedEarnings() {
        return estimatedEarnings;
    }

    public void setEstimatedEarnings(Long estimatedEarnings) {
        this.estimatedEarnings = estimatedEarnings;
    }

    public Double getAveragePatientAge() {
        return averagePatientAge;
    }

    public void setAveragePatientAge(Double averagePatientAge) {
        this.averagePatientAge = averagePatientAge;
    }
}
