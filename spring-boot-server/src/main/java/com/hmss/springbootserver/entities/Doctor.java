package com.hmss.springbootserver.entities;

import jakarta.persistence.*;

@Entity
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private Long idUser;
    private Long idHospital;
    private Long idSpeciality;
    private String university;
    private String description;
    private String location;

    public Doctor(Long id, Long idUser, Long idHospital, Long idSpeciality, String university, String description, String location) {
        this.id = id;
        this.idUser = idUser;
        this.idHospital = idHospital;
        this.idSpeciality = idSpeciality;
        this.university = university;
        this.description = description;
        this.location = location;
    }

    public Doctor() {
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public void setIdHospital(Long idHospital) {
        this.idHospital = idHospital;
    }

    public void setIdSpeciality(Long idSpeciality) {
        this.idSpeciality = idSpeciality;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getIdUser() {
        return idUser;
    }

    public Long getIdHospital() {
        return idHospital;
    }

    public Long getIdSpeciality() {
        return idSpeciality;
    }

    public String getUniversity() {
        return university;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
