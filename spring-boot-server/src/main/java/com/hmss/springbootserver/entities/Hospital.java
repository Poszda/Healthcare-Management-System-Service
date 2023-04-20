package com.hmss.springbootserver.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Hospital { //owning side //
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String location;
    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(
            name = "hospital_speciality",
            joinColumns = @JoinColumn(name = "hospital_id"),
            inverseJoinColumns = @JoinColumn(name = "speciality_id"))
    Set<Speciality> specialitySet = new HashSet<>();


    public void addSpeciality(Speciality speciality){
        specialitySet.add(speciality);
        speciality.getHospitalSet().add(this);
    }

    public void removeSpeciality(Speciality speciality) {
        specialitySet.remove(speciality);
        speciality.getHospitalSet().remove(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Set<Speciality> getSpecialitySet() {
        return specialitySet;
    }

    public void setSpecialitySet(Set<Speciality> specialitySet) {
        this.specialitySet = specialitySet;
    }
}
