package com.hmss.springbootserver.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "\"procedure\"")
public class Procedure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private int duration; //minutes
    private float price;
    @ManyToMany(mappedBy = "procedureSet", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    Set<Hospital> hospitalSet = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="speciality_id")
    private Speciality speciality;

    @OneToOne(mappedBy = "procedure", cascade = CascadeType.ALL) // aici cascade trebuie verificat // nu pun optional=false
    private Appointment appointment;

    public void addHospital(Hospital hospital) {
        hospitalSet.add(hospital);
        hospital.getProcedureSet().add(this);
    }

    // Remove a Hospital from the hospitalSet
    public void removeHospital(Hospital hospital) {
        hospitalSet.remove(hospital);
        hospital.getProcedureSet().remove(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Set<Hospital> getHospitalSet() {
        return hospitalSet;
    }

    public void setHospitalSet(Set<Hospital> hospitalSet) {
        this.hospitalSet = hospitalSet;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }
}
