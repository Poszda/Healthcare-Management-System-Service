package com.hmss.springbootserver.services;

import com.hmss.springbootserver.DTOs.doctor.*;
import com.hmss.springbootserver.DTOs.patient.PatientBioDTO;
import com.hmss.springbootserver.DTOs.patient.PatientProfileDTO;
import com.hmss.springbootserver.DTOs.patient.PatientWithUserDTO;
import com.hmss.springbootserver.entities.*;
import com.hmss.springbootserver.enums.AppFileType;
import com.hmss.springbootserver.enums.UserType;
import com.hmss.springbootserver.exceptions.EmailAlreadyInUseException;
import com.hmss.springbootserver.exceptions.FileStorageException;
import com.hmss.springbootserver.exceptions.ResourceNotFoundException;
import com.hmss.springbootserver.mappers.DoctorMapper;
import com.hmss.springbootserver.mappers.PatientMapper;
import com.hmss.springbootserver.repositories.*;
import com.hmss.springbootserver.utils.models.projections.DoctorSearchProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final AdminRepository adminRepository;
    private final HospitalRepository hospitalRepository;
    private final SpecialityRepository specialityRepository;
    private final UserRepository userRepository;
    private final DiagnosticRepository diagnosticRepository;
    private final MedicationRepository medicationRepository;
    private final FileMetadataRepository fileMetadataRepository;
    private final FileService fileService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(DoctorRepository doctorRepository, PatientRepository patientRepository, AdminRepository adminRepository,
                       HospitalRepository hospitalRepository,
                       SpecialityRepository specialityRepository,
                       UserRepository userRepository,
                       DiagnosticRepository diagnosticRepository,
                       MedicationRepository medicationRepository,
                       FileMetadataRepository fileMetadataRepository, FileService fileService, PasswordEncoder passwordEncoder) {
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.adminRepository = adminRepository;
        this.hospitalRepository = hospitalRepository;
        this.specialityRepository = specialityRepository;
        this.userRepository = userRepository;
        this.diagnosticRepository = diagnosticRepository;
        this.medicationRepository = medicationRepository;
        this.fileMetadataRepository = fileMetadataRepository;
        this.fileService = fileService;
        this.passwordEncoder = passwordEncoder;
    }

    public List<DoctorDTO> getDoctorsById(List<Long> ids){
        List<Doctor> doctors = this.doctorRepository.findAllById(ids);
        return DoctorMapper.INSTANCE.toDoctorDTOList(doctors);
    }

    public List<DoctorSuggestionInfoDTO> getDoctorsSuggestionInfo(List<Long> ids) {
        List<DoctorSuggestionInfoDTO> doctors = DoctorMapper.INSTANCE.toDoctorSuggestionInfoDTOList(doctorRepository.findDoctorsSuggestionInfo(ids));
        doctors.stream().forEach(el -> el.setProfileImage(fileService.getFullPath(el.getProfileImage())));
        return doctors;
    }

    public List<DoctorWithUserAndSpecialityDTO> getHospitalDoctorsWithSpeciality(Long hospitalId) {
        System.out.println(hospitalId);
        var x= this.doctorRepository.findHospitalDoctorsWithSpeciality(hospitalId);
        return DoctorMapper.INSTANCE.toDoctorWithUserAndSpecialityDTOList(x);
    }

    public DoctorDTO createDoctor(CreateDoctorRequestDTO createDoctorRequest) {
        User user = new User();
        Doctor doctor = new Doctor();
        Optional<Hospital> hospitalOptional = this.hospitalRepository.findById(createDoctorRequest.getHospitalId());
        Optional<Speciality> specialityOptional = this.specialityRepository.findById(createDoctorRequest.getSpecialityId());
        Optional<User> optionalUser = this.userRepository.findByEmail(createDoctorRequest.getEmail());

        if(hospitalOptional.isEmpty()) throw new ResourceNotFoundException("Hospital not found");
        if(specialityOptional.isEmpty()) throw new ResourceNotFoundException("Speciality not found");
        if(optionalUser.isPresent()) throw new EmailAlreadyInUseException("Email address already in use");
        Hospital hospital = hospitalOptional.get();
        Speciality speciality = specialityOptional.get();

        user.setUserType(UserType.DOCTOR);
        user.setEmail(createDoctorRequest.getEmail());
        user.setPassword(passwordEncoder.encode(createDoctorRequest.getPassword()));
        user.setFirstName(createDoctorRequest.getFirstName());
        user.setLastName(createDoctorRequest.getLastName());
        doctor.setUser(user);
        doctor.setProgramStart(createDoctorRequest.getProgramStart());
        doctor.setProgramEnd(createDoctorRequest.getProgramEnd());
        doctor.setHospital(hospital);
        doctor.setSpeciality(speciality);

        return DoctorMapper.INSTANCE.toDoctorDTO(this.doctorRepository.save(doctor));
    }

    public void deleteDoctor(Long id) {
        if (!doctorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Doctor not found");
        }

        doctorRepository.deleteById(id);
    }

    public DoctorProfileDTO getDoctorProfile(Long doctorId) {
        Optional<Doctor> doctorOptional = doctorRepository.findById(doctorId);
        if(doctorOptional.isEmpty()) throw new ResourceNotFoundException("Doctor not found");
        String profileImagePath = this.fileMetadataRepository.findByUserIdAndType(doctorOptional.get().getUser().getId(),AppFileType.PROFILE_IMAGE)
                .map(FileMetadata::getPath)
                .orElse(null);

        return DoctorMapper.INSTANCE.toDoctorProfileDTO(doctorOptional.get(), this.fileService.getFullPath(profileImagePath));
    }

    public UpdatedDoctorProfileDTO updateDoctorProfile(Long doctorId, MultipartFile profileImage, String university, String description) {
        Optional<Doctor> doctorOptional = this.doctorRepository.findById(doctorId);
        if (doctorOptional.isEmpty()) throw new ResourceNotFoundException("Doctor not found");
        Doctor doctor = doctorOptional.get();
        doctor.setDescription(description);
        doctor.setUniversity(university);
        try {
            if(profileImage != null) {
               this.fileService.saveFileAndMetadata(profileImage, doctor.getUser(), AppFileType.PROFILE_IMAGE,true);
            }
            Doctor updatedDoctor = this.doctorRepository.save(doctor);
            String profileImagePath = this.fileMetadataRepository.findByUserIdAndType(updatedDoctor.getUser().getId(), AppFileType.PROFILE_IMAGE)
                    .map(FileMetadata::getPath)
                    .orElse(null);
            return new UpdatedDoctorProfileDTO(updatedDoctor.getUniversity(), updatedDoctor.getDescription(), this.fileService.getFullPath(profileImagePath));
        } catch (IOException e) {
            throw new FileStorageException("Failed to update doctor profile picture: " + e.getMessage());
        }
    }

    public PatientProfileDTO getPatientProfile(Long patientId) {
        Optional<Patient> patientOptional = this.patientRepository.findById(patientId);
        if(patientOptional.isEmpty()) throw new ResourceNotFoundException("Patient not found");
        Patient patient = patientOptional.get();

        PatientWithUserDTO patientDTO = PatientMapper.INSTANCE.toPatientWithUserDTO(patient);
        String fileMetadata = this.fileMetadataRepository.findByUserIdAndType(patient.getUser().getId(),AppFileType.PROFILE_IMAGE)
                .map(FileMetadata::getPath)
                .orElse(null);

        List<String> diagnosticsName = this.diagnosticRepository.findPatientDiagnosticsName(patientId,LocalDate.now().minusMonths(6));
        List<String> medicationsName3Months = this.medicationRepository.findPatientMedicationsName(patientId,LocalDate.now().minusMonths(3),LocalDate.now());
        List<String> medicationsName6Months = this.medicationRepository.findPatientMedicationsName(patientId,LocalDate.now().minusMonths(6),LocalDate.now().minusMonths(3));

        return new PatientProfileDTO(
                patientDTO,
                diagnosticsName,
                medicationsName3Months,
                medicationsName6Months,
                this.fileService.getFullPath(fileMetadata)
        );
    }

    public void updatePatientProfile(Long patientId, String firstName, String lastName,
                                                       Integer height, Integer weight, String bloodType,
                                                       String phone, LocalDate birthDate,
                                                       MultipartFile profileImage) {
        Optional<Patient> patientOptional = this.patientRepository.findById(patientId);
        if (patientOptional.isEmpty()) throw new ResourceNotFoundException("Patient not found");
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
            this.patientRepository.save(patient);
        } catch (IOException e) {
            throw new FileStorageException("Failed to update patient profile picture: " + e.getMessage());
        }

    }

    public PatientBioDTO getPatientBio(Long patientId) {
        Optional<Patient> patientOptional = this.patientRepository.findById(patientId);
        if(patientOptional.isEmpty()) throw new ResourceNotFoundException("Patient not found");
        String profileImagePath = this.fileMetadataRepository.findByUserIdAndType(patientOptional.get().getUser().getId(),AppFileType.PROFILE_IMAGE).map(FileMetadata::getPath).orElse(null);
        return PatientMapper.INSTANCE.toPatientBioDTO(patientOptional.get(),this.fileService.getFullPath(profileImagePath));
    }

    public List<DoctorSearchDTO> getSearchedDoctors(String name, Long specialityId) {
        List<DoctorSearchProjection> list = this.doctorRepository.findSearchedDoctors(name,specialityId);
        List<DoctorSearchDTO> listDTO = DoctorMapper.INSTANCE.toDoctorSearchDTOList(list).stream()
                .map(el ->{
                    el.setProfileImage(fileService.getFullPath(el.getProfileImage()));
                    return el;
                }).collect(Collectors.toList());

        return listDTO;
    }
}
