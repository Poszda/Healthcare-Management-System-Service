package com.hmss.springbootserver.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "doctor")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String university;
    private String description;
    private LocalTime programStart;
    private LocalTime programEnd;
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy="doctor") // cascade
    private List<Appointment> appointments;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="speciality_id")
    private Speciality speciality;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="hospital_id")
    private Hospital hospital;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public LocalTime getProgramStart() {
        return programStart;
    }

    public void setProgramStart(LocalTime programStart) {
        this.programStart = programStart;
    }

    public LocalTime getProgramEnd() {
        return programEnd;
    }

    public void setProgramEnd(LocalTime programEnd) {
        this.programEnd = programEnd;
    }
}
