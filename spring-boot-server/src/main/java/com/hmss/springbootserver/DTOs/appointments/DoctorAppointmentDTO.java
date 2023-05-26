package com.hmss.springbootserver.DTOs.appointments;

import com.hmss.springbootserver.enums.AppointmentStatus;
import com.hmss.springbootserver.utils.models.projections.DoctorAppointmentProjection;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DoctorAppointmentDTO {
    private Long id;
    private Integer duration;
    private LocalDate date;
    private LocalTime time;
    private String procedureName;
    private Long patientId;
    private String lastName;
    private String firstName;
    private String phone;
    private Integer age;

    private AppointmentStatus status;

    public DoctorAppointmentDTO(Long id, Integer duration, LocalDate date, LocalTime time, String procedureName, Long patientId, String lastName, String firstName, String phone, Integer age, AppointmentStatus status) {
        this.id = id;
        this.duration = duration;
        this.date = date;
        this.time = time;
        this.procedureName = procedureName;
        this.patientId = patientId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.phone = phone;
        this.age = age;
        this.status = status;
    }

    public DoctorAppointmentDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getProcedureName() {
        return procedureName;
    }

    public void setProcedureName(String procedureName) {
        this.procedureName = procedureName;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }
}
