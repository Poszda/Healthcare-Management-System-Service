package com.hmss.springbootserver.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String cnp;
    private String phone;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})  //delete this
    @OneToOne(fetch = FetchType.LAZY) // nu merge la one to one daca e non owning side parca //delte fetch type // problema e ca inca merge
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "FK_USER_PATIENT"))
    private User user;
    @OneToMany(mappedBy="patient",fetch = FetchType.LAZY) // fetch functioneaza bine
    private List<Appointment> appointmentList;


    public Patient(Long id, User user, String cnp, String phone, List<Appointment> appointmentList) {
        this.id = id;
        this.user = user;
        this.cnp = cnp;
        this.phone = phone;
        this.appointmentList = appointmentList;
    }

    public Patient() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public List<Appointment> getProcedureList() {
        return appointmentList;
    }

    public void setProcedureList(List<Appointment> medicalProcedures) {
        this.appointmentList = appointmentList;
    }
}
