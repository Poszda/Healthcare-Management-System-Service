package com.hmss.springbootserver.DTOs;

import com.hmss.springbootserver.entities.Appointment;
import com.hmss.springbootserver.entities.User;


import java.util.List;

public class PatientDTO {
    private Long id;
    private String cnp;
    private String phone;
    private UserDTO user;
    //private List<Appointment> appointmentList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
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
}
