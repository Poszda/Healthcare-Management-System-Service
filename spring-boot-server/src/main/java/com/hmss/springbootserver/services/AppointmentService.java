package com.hmss.springbootserver.services;

import com.hmss.springbootserver.DTOs.hospital.HospitalWithDoctorsDTO;
import com.hmss.springbootserver.mappers.HospitalMapper;
import com.hmss.springbootserver.repositories.AppointmentRepository;
import com.hmss.springbootserver.repositories.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {
    private final HospitalRepository hospitalRepository;
    private final AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentService(HospitalRepository hospitalRepository, AppointmentRepository appointmentRepository) {
        this.hospitalRepository = hospitalRepository;
        this.appointmentRepository = appointmentRepository;
    }

    public List<HospitalWithDoctorsDTO> getHospitalsAndDoctorsRecommendations(List<String> counties, long speciality){
        var hospitals = this.hospitalRepository.findPossibleHospitalsAndDoctorsForAppointments(counties,speciality);
        return HospitalMapper.INSTANCE.toHospitalWithDoctorsDTOList(hospitals);
    }

}
