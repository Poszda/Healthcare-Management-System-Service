package com.hmss.springbootserver.services;

import com.hmss.springbootserver.DTOs.doctor.CreateDoctorRequestDTO;
import com.hmss.springbootserver.DTOs.doctor.DoctorDTO;
import com.hmss.springbootserver.DTOs.doctor.DoctorWithUserAndHospitalDTO;
import com.hmss.springbootserver.DTOs.doctor.DoctorWithUserAndSpecialityDTO;
import com.hmss.springbootserver.entities.Doctor;
import com.hmss.springbootserver.entities.Hospital;
import com.hmss.springbootserver.entities.Speciality;
import com.hmss.springbootserver.entities.User;
import com.hmss.springbootserver.enums.UserType;
import com.hmss.springbootserver.mappers.DoctorMapper;
import com.hmss.springbootserver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final AdminRepository adminRepository;
    private final HospitalRepository hospitalRepository;
    private final SpecialityRepository specialityRepository;
    private final UserRepository userRepository;

    public UserService(DoctorRepository doctorRepository, PatientRepository patientRepository, AdminRepository adminRepository,
                       HospitalRepository hospitalRepository,
                       SpecialityRepository specialityRepository,
                       UserRepository userRepository) {
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.adminRepository = adminRepository;
        this.hospitalRepository = hospitalRepository;
        this.specialityRepository = specialityRepository;
        this.userRepository = userRepository;
    }

    public List<DoctorDTO> getDoctorsById(List<Long> ids){
        List<Doctor> doctors = this.doctorRepository.findAllById(ids);
        return DoctorMapper.INSTANCE.toDoctorDTOList(doctors);
    }

    public List<DoctorWithUserAndHospitalDTO> getDoctorsWithUsersAndHospitalsById(List<Long> ids) {
        List<Doctor> doctors = this.doctorRepository.findAllById(ids);
        return DoctorMapper.INSTANCE.toDoctorWithUserAndHospitalDTOList(doctors);
    }

    public List<DoctorWithUserAndSpecialityDTO> getHospitalDoctorsWithSpeciality(Long hospitalId) {
        System.out.println(hospitalId);
        var x= this.doctorRepository.findHospitalDoctorsWithSpeciality(hospitalId);
        return DoctorMapper.INSTANCE.toDoctorWithUserAndSpecialityDTOList(x);
    }

    public ResponseEntity<Object> createDoctor(CreateDoctorRequestDTO createDoctorRequest) {
        User user = new User();
        Doctor doctor = new Doctor();
        Optional<Hospital> hospitalOptional = this.hospitalRepository.findById(createDoctorRequest.getHospitalId());
        Optional<Speciality> specialityOptional = this.specialityRepository.findById(createDoctorRequest.getSpecialityId());
        Optional<User> optionalUser = this.userRepository.findByEmail(createDoctorRequest.getEmail());

        if(hospitalOptional.isEmpty()) return new ResponseEntity<>("Hospital not found", HttpStatus.BAD_REQUEST);
        if(specialityOptional.isEmpty()) return new ResponseEntity<>("Speciality not found", HttpStatus.BAD_REQUEST);
        if(optionalUser.isPresent()) return  new ResponseEntity<>("Email already exists", HttpStatus.CONFLICT);
        Hospital hospital = hospitalOptional.get();
        Speciality speciality = specialityOptional.get();

        user.setUserType(UserType.DOCTOR);
        user.setEmail(createDoctorRequest.getEmail());
        user.setPassword(createDoctorRequest.getPassword());
        user.setFirstName(createDoctorRequest.getFirstName());
        user.setLastName(createDoctorRequest.getLastName());
        doctor.setUser(user);
        doctor.setProgramStart(createDoctorRequest.getProgramStart());
        doctor.setProgramEnd(createDoctorRequest.getProgramEnd());
        doctor.setHospital(hospital);
        doctor.setSpeciality(speciality);

        return new ResponseEntity<>(DoctorMapper.INSTANCE.toDoctorDTO(this.doctorRepository.save(doctor)),HttpStatus.ACCEPTED);
    }

    public ResponseEntity<Object> deleteDoctor(Long id) {
        this.doctorRepository.deleteById(id);
        if(this.doctorRepository.findById(id).isEmpty()){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(" Appointment deletion failed",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
