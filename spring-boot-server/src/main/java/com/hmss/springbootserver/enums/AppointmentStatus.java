package com.hmss.springbootserver.enums;

public enum AppointmentStatus {
    UPCOMING("Upcoming"),
    IN_PROGRESS("In progress"),
    REVIEWED("Reviewed");
    private final String status;
    AppointmentStatus(String status){
        this.status = status;
    }
    public String getStatus() {
        return status;
    }
}
