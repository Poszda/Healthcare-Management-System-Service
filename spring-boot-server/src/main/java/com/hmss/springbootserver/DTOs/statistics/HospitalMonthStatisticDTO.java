package com.hmss.springbootserver.DTOs.statistics;

public class HospitalMonthStatisticDTO {
    private String month;
    private Integer appointments;
    private Integer total;
    private Integer order;

    public HospitalMonthStatisticDTO(String month, Integer appointments, Integer total, Integer order) {
        this.month = month;
        this.appointments = appointments;
        this.total = total;
        this.order = order;
    }

    public HospitalMonthStatisticDTO() {
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Integer getAppointments() {
        return appointments;
    }

    public void setAppointments(Integer appointments) {
        this.appointments = appointments;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
}
