package com.hmss.springbootserver.services;

import com.hmss.springbootserver.DTOs.doctor.*;
import com.hmss.springbootserver.DTOs.files.FileMetadataDTO;
import com.hmss.springbootserver.DTOs.patient.PatientProfileDTO;
import com.hmss.springbootserver.DTOs.patient.PatientWithUserWithFileMetadataDTO;
import com.hmss.springbootserver.DTOs.patient.UpdatePatientProfileRequest;
import com.hmss.springbootserver.DTOs.user.UserWithFileMetadataDTO;
import com.hmss.springbootserver.entities.*;
import com.hmss.springbootserver.enums.AppFileType;
import com.hmss.springbootserver.enums.UserType;
import com.hmss.springbootserver.mappers.AppointmentMapper;
import com.hmss.springbootserver.mappers.DoctorMapper;
import com.hmss.springbootserver.mappers.PatientMapper;
import com.hmss.springbootserver.repositories.*;
import com.hmss.springbootserver.utils.models.projections.DoctorSearchProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    private final FileService fileService;

    public UserService(DoctorRepository doctorRepository, PatientRepository patientRepository, AdminRepository adminRepository,
                       HospitalRepository hospitalRepository,
                       SpecialityRepository specialityRepository,
                       UserRepository userRepository,
                       DiagnosticRepository diagnosticRepository,
                       MedicationRepository medicationRepository,
                       FileService fileService) {
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.adminRepository = adminRepository;
        this.hospitalRepository = hospitalRepository;
        this.specialityRepository = specialityRepository;
        this.userRepository = userRepository;
        this.diagnosticRepository = diagnosticRepository;
        this.medicationRepository = medicationRepository;
        this.fileService = fileService;
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
        Optional<Doctor> doctorOptional = doctorRepository.findDoctorAndProfileImage(doctorId);
        if(doctorOptional.isEmpty())  return new ResponseEntity<>("Doctor not found", HttpStatus.NOT_FOUND);
        DoctorProfileDTO doctorProfileDTO = DoctorMapper.INSTANCE.toDoctorProfileDTO(doctorOptional.get());
        updateUserProfileImageLink(doctorProfileDTO.getUser());
        return new ResponseEntity<>(doctorProfileDTO,HttpStatus.OK);
    }

    public ResponseEntity<Object> updateDoctorProfile(Long doctorId, MultipartFile profileImage, String university, String description) {
        Optional<Doctor> doctorOptional = this.doctorRepository.findById(doctorId);
        if(doctorOptional.isEmpty())  return new ResponseEntity<>("Doctor not found", HttpStatus.NOT_FOUND);
        Doctor doctor = doctorOptional.get();
        doctor.setDescription(description);
        doctor.setUniversity(university);
        try {
            if(profileImage != null) {
                this.fileService.saveFileAndMetadata(profileImage,doctor.getUser(), AppFileType.PROFILE_IMAGE,true);
            }
            Doctor updatedDoctor = this.doctorRepository.save(doctor);
            return new ResponseEntity<>(new UpdateDoctorUniversityAndDescriptionRequest(updatedDoctor.getUniversity(), updatedDoctor.getDescription(), null), HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to update doctor profile picture: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> getPatientProfile(Long patientId) {
        Optional<Patient> patientOptional = this.patientRepository.findPatientAndProfileImage(patientId);
        if(patientOptional.isEmpty())  return new ResponseEntity<>("Patient not found", HttpStatus.NOT_FOUND);
        Patient patient = patientOptional.get();

        PatientWithUserWithFileMetadataDTO patientDTO = PatientMapper.INSTANCE.toPatientWithUserWithFileMetadataDTO(patient);
        updateUserProfileImageLink(patientDTO.getUser());
        List<String> diagnosticsName = this.diagnosticRepository.findPatientDiagnosticsName(patientId,LocalDate.now().minusMonths(6));
        List<String> medicationsName3Months = this.medicationRepository.findPatientMedicationsName(patientId,LocalDate.now().minusMonths(3),LocalDate.now());
        List<String> medicationsName6Months = this.medicationRepository.findPatientMedicationsName(patientId,LocalDate.now().minusMonths(6),LocalDate.now().minusMonths(3));

        PatientProfileDTO patientProfile = new PatientProfileDTO(
                patientDTO,
                diagnosticsName,
                medicationsName3Months,
                medicationsName6Months
        );

        return new ResponseEntity<>(patientProfile,HttpStatus.OK);
    }

    public ResponseEntity<Object> updatePatientProfile(Long patientId, String firstName, String lastName,
                                                       Integer height, Integer weight, String bloodType,
                                                       String phone, LocalDate birthDate,
                                                       MultipartFile profileImage) {
        Optional<Patient> patientOptional = this.patientRepository.findById(patientId);
        if (patientOptional.isEmpty()) return new ResponseEntity<>("Patient not found", HttpStatus.NOT_FOUND);
        Patient patient = patientOptional.get();
        patient.getUser().setFirstName(firstName);
        patient.getUser().setLastName(lastName);
        patient.setPhone(phone);
        patient.setBirthDate(birthDate);
        patient.setBloodType(bloodType);
        patient.setHeight(height);
        patient.setWeight(weight);
        try {
            if(profileImage != null) {
                this.fileService.saveFileAndMetadata(profileImage,patient.getUser(), AppFileType.PROFILE_IMAGE,true);
            }
            Patient updatedPatient = this.patientRepository.save(patient);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to update patient profile picture: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ResponseEntity<Object> getPatient(Long patientId) {
        Optional<Patient> patientOptional = this.patientRepository.findById(patientId);
        if(patientOptional.isEmpty()) return new ResponseEntity<>("Patient not found", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(PatientMapper.INSTANCE.toPatientDTO(patientOptional.get()),HttpStatus.OK);
    }

    public List<DoctorSearchDto> getSearchedDoctors(String name,Long specialityId) {
        List<DoctorSearchProjection> list = this.doctorRepository.findSearchedDoctors(name,specialityId);
        List<DoctorSearchDto> listDTO = DoctorMapper.INSTANCE.toDoctorSearchDTOList(list).stream()
                .map(el ->{
                    el.setProfileImage(fileService.getFullPath(el.getProfileImage()));
                    return el;
                }).collect(Collectors.toList());

        return listDTO;
    }

    private void updateUserProfileImageLink(UserWithFileMetadataDTO user){
        Optional<FileMetadataDTO> profileImageOptional =  user.getFileMetadataList().stream().filter(el -> AppFileType.PROFILE_IMAGE == el.getType()).findFirst();
        if(profileImageOptional.isPresent()){
            FileMetadataDTO profileImage = profileImageOptional.get();
            profileImage.setPath(fileService.getFullPath(profileImage.getPath()));
        }
    }
}
