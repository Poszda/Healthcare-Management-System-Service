package com.hmss.springbootserver.services;

import com.hmss.springbootserver.DTOs.doctor.*;
import com.hmss.springbootserver.DTOs.patient.PatientProfileDTO;
import com.hmss.springbootserver.DTOs.patient.UpdatePatientProfileRequest;
import com.hmss.springbootserver.entities.*;
import com.hmss.springbootserver.enums.UserType;
import com.hmss.springbootserver.mappers.AppointmentMapper;
import com.hmss.springbootserver.mappers.DoctorMapper;
import com.hmss.springbootserver.mappers.PatientMapper;
import com.hmss.springbootserver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
    private final DiagnosticRepository diagnosticRepository;
    private final MedicationRepository medicationRepository;

    public UserService(DoctorRepository doctorRepository, PatientRepository patientRepository, AdminRepository adminRepository,
                       HospitalRepository hospitalRepository,
                       SpecialityRepository specialityRepository,
                       UserRepository userRepository,
                       DiagnosticRepository diagnosticRepository,
                       MedicationRepository medicationRepository) {
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.adminRepository = adminRepository;
        this.hospitalRepository = hospitalRepository;
        this.specialityRepository = specialityRepository;
        this.userRepository = userRepository;
        this.diagnosticRepository = diagnosticRepository;
        this.medicationRepository = medicationRepository;
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
            return new ResponseEntity<>("Appointment deletion failed",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> getDoctorProfile(Long doctorId) {
        Optional<Doctor> doctorOptional = this.doctorRepository.findById(doctorId);
        if(doctorOptional.isEmpty())  return new ResponseEntity<>("Doctor not found", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(AppointmentMapper.INSTANCE.toDoctorWithUserAndHospitalAndSpecialityDTO(doctorOptional.get()),HttpStatus.OK);
    }

    public ResponseEntity<Object> updateDoctorUniversityAndDescription(Long doctorId, UpdateDoctorUniversityAndDescriptionRequest request) {
        Optional<Doctor> doctorOptional = this.doctorRepository.findById(doctorId);
        if(doctorOptional.isEmpty())  return new ResponseEntity<>("Doctor not found", HttpStatus.NOT_FOUND);
        Doctor doctor = doctorOptional.get();
        doctor.setDescription(request.getDescription());
        doctor.setUniversity(request.getUniversity());
        Doctor updatedDoctor = this.doctorRepository.save(doctor);
        return new ResponseEntity<>(new UpdateDoctorUniversityAndDescriptionRequest(updatedDoctor.getUniversity(), updatedDoctor.getDescription()),HttpStatus.OK);
    }

    public Object getPatientProfile(Long patientId) {
        Optional<Patient> patientOptional = this.patientRepository.findById(patientId);
        if(patientOptional.isEmpty())  return new ResponseEntity<>("Patient not found", HttpStatus.NOT_FOUND);
        Patient patient = patientOptional.get();
        List<String> diagnosticsName = this.diagnosticRepository.findPatientDiagnosticsName(patientId,LocalDate.now().minusMonths(6));
        List<String> medicationsName3Months = this.medicationRepository.findPatientMedicationsName(patientId,LocalDate.now().minusMonths(3),LocalDate.now());
        List<String> medicationsName6Months = this.medicationRepository.findPatientMedicationsName(patientId,LocalDate.now().minusMonths(6),LocalDate.now().minusMonths(3));
        PatientProfileDTO patientProfile = new PatientProfileDTO(
                PatientMapper.INSTANCE.toPatientWithUserDTO(patient),
                diagnosticsName,
                medicationsName3Months,
                medicationsName6Months
        );

        return patientProfile;
    }

    public ResponseEntity<Object> updatePatientProfile(Long patientId, UpdatePatientProfileRequest request) {
        Optional<Patient> patientOptional = this.patientRepository.findById(patientId);
        if (patientOptional.isEmpty()) return new ResponseEntity<>("Patient not found", HttpStatus.NOT_FOUND);
        Patient patient = patientOptional.get();
        patient.getUser().setFirstName(request.getFirstName());
        patient.getUser().setLastName(request.getLastName());
        patient.setPhone(request.getPhone());
        patient.setBirthDate(request.getBirthDate());
        patient.setBloodType(request.getBloodType());
        patient.setHeight(request.getHeight());
        patient.setWeight(request.getWeight());
        Patient updatedPatient = this.patientRepository.save(patient);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<Object> getPatient(Long patientId) {
        Optional<Patient> patientOptional = this.patientRepository.findById(patientId);
        if(patientOptional.isEmpty()) return new ResponseEntity<>("Patient not found", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(PatientMapper.INSTANCE.toPatientDTO(patientOptional.get()),HttpStatus.OK);
    }

    public List<DoctorWithUserDTO> getSearchedDoctors(String name,Long specialityId) {
        List<Doctor> doctors =  this.doctorRepository.findDoctorByNameAndSpeciality(name,specialityId);
        return DoctorMapper.INSTANCE.toDoctorWithUserDTOList(doctors);
    }
}
