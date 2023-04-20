package com.hmss.springbootserver.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Speciality {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    @ManyToMany(mappedBy = "specialitySet", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    Set<Hospital> hospitalSet = new HashSet<>();

    // Add a Hospital to the hospitalSet
    public void addHospital(Hospital hospital) {
        hospitalSet.add(hospital);
        hospital.getSpecialitySet().add(this);
    }

    // Remove a Hospital from the hospitalSet
    public void removeHospital(Hospital hospital) {
        hospitalSet.remove(hospital);
        hospital.getSpecialitySet().remove(this);
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

    public Set<Hospital> getHospitalSet() {
        return hospitalSet;
    }

    public void setHospitalSet(Set<Hospital> hospitalSet) {
        this.hospitalSet = hospitalSet;
    }
}
