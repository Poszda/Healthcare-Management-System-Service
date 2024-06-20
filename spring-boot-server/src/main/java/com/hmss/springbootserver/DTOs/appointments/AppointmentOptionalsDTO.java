package com.hmss.springbootserver.DTOs.appointments;

import com.hmss.springbootserver.DTOs.doctor.DoctorWithUserDTO;
import com.hmss.springbootserver.DTOs.hospital.HospitalDTO;

import java.util.List;

public class AppointmentOptionalsDTO {
    List<HospitalDTO> hospitals;
    List<DoctorWithUserDTO> doctors;

    public List<HospitalDTO> getHospitals() {
        return hospitals;
    }

    public void setHospitals(List<HospitalDTO> hospitals) {
        this.hospitals = hospitals;
    }

    public List<DoctorWithUserDTO> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<DoctorWithUserDTO> doctors) {
        this.doctors = doctors;
    }
}
