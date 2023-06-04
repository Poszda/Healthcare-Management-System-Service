package com.hmss.springbootserver.DTOs.statistics;

public class DoctorPatientsVisitsByAgeGroupDTO {
    private String month;
    private int youngPatients;
    private int adultPatients;
    private int OldPatients;

    public DoctorPatientsVisitsByAgeGroupDTO() {
    }

    public DoctorPatientsVisitsByAgeGroupDTO(String month, int youngPatients, int adultPatients, int oldPatients) {
        this.month = month;
        this.youngPatients = youngPatients;
        this.adultPatients = adultPatients;
        OldPatients = oldPatients;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getYoungPatients() {
        return youngPatients;
    }

    public void setYoungPatients(int youngPatients) {
        this.youngPatients = youngPatients;
    }

    public int getAdultPatients() {
        return adultPatients;
    }

    public void setAdultPatients(int adultPatients) {
        this.adultPatients = adultPatients;
    }

    public int getOldPatients() {
        return OldPatients;
    }

    public void setOldPatients(int oldPatients) {
        OldPatients = oldPatients;
    }
}
