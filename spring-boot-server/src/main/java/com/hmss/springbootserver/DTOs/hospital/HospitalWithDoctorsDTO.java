package com.hmss.springbootserver.DTOs.hospital;

import com.hmss.springbootserver.DTOs.doctor.DoctorDTO;
import com.hmss.springbootserver.DTOs.doctor.DoctorWithUserDTO;
import com.hmss.springbootserver.entities.Doctor;

import java.util.List;

public class HospitalWithDoctorsDTO {
    private Long id;
    private String name;
    private String county;
    private String locality;
    private List<DoctorWithUserDTO> doctors;

    public HospitalWithDoctorsDTO(Long id, String name, String county, String locality, List<DoctorWithUserDTO> doctors) {
        this.id = id;
        this.name = name;
        this.county = county;
        this.locality = locality;
        this.doctors = doctors;
    }

    public HospitalWithDoctorsDTO() {
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

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public List<DoctorWithUserDTO> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<DoctorWithUserDTO> doctors) {
        this.doctors = doctors;
    }
}
