package com.hmss.springbootserver.services;

import com.hmss.springbootserver.DTOs.doctor.DoctorDTO;
import com.hmss.springbootserver.DTOs.doctor.DoctorWithUserAndHospitalDTO;
import com.hmss.springbootserver.entities.Doctor;
import com.hmss.springbootserver.mappers.DoctorMapper;
import com.hmss.springbootserver.repositories.AdminRepository;
import com.hmss.springbootserver.repositories.DoctorRepository;
import com.hmss.springbootserver.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final AdminRepository adminRepository;

    public UserService(DoctorRepository doctorRepository, PatientRepository patientRepository, AdminRepository adminRepository) {
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.adminRepository = adminRepository;
    }

    public List<DoctorDTO> getDoctorsById(List<Long> ids){
        List<Doctor> doctors = this.doctorRepository.findAllById(ids);
        return DoctorMapper.INSTANCE.toDoctorDTOList(doctors);
    }

    public List<DoctorWithUserAndHospitalDTO> getDoctorsWithUsersAndHospitalsById(List<Long> ids) {
        List<Doctor> doctors = this.doctorRepository.findAllById(ids);
        return DoctorMapper.INSTANCE.toDoctorWithUserAndHospitalDTOList(doctors);
    }
}
