package com.hmss.springbootserver.DTOs;

import com.hmss.springbootserver.entities.Appointment;

import java.util.List;

public class PatientDTO {
    private Long id;
    private String phone;
    private UserDTO user;

    private List<AppointmentDTO> appointments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public List<AppointmentDTO> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<AppointmentDTO> appointments) {
        this.appointments = appointments;
    }
}
