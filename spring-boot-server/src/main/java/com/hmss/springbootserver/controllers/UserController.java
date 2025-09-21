package com.hmss.springbootserver.controllers;

import com.hmss.springbootserver.DTOs.doctor.*;
import com.hmss.springbootserver.DTOs.patient.PatientBioDTO;
import com.hmss.springbootserver.DTOs.patient.PatientProfileDTO;
import com.hmss.springbootserver.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(path = "api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getDoctorsById/{ids}")
    public ResponseEntity<List<DoctorDTO>> getDoctorsById(@PathVariable("ids") List<Long> ids){
        return new ResponseEntity<>(this.userService.getDoctorsById(ids), HttpStatus.OK);
    }

    @GetMapping("/getDoctorsSuggestionInfo/{ids}")
    public ResponseEntity<List<DoctorSuggestionInfoDTO>> getDoctorsSuggestionInfo(@PathVariable("ids") List<Long> ids){
        return new ResponseEntity<>(this.userService.getDoctorsSuggestionInfo(ids), HttpStatus.OK);
    }

    @GetMapping("/getHospitalDoctorsWithSpeciality/{hospitalId}")
    public ResponseEntity<List<DoctorWithUserAndSpecialityDTO>> getHospitalDoctorsWithSpeciality(@PathVariable("hospitalId") Long hospitalId){
        return new ResponseEntity<>(this.userService.getHospitalDoctorsWithSpeciality(hospitalId), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/createDoctor")
    public ResponseEntity<DoctorDTO> createDoctor(@Valid @RequestBody CreateDoctorRequestDTO createDoctorRequest){
        return new ResponseEntity<>(this.userService.createDoctor(createDoctorRequest), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deleteDoctor/{id}")
    public ResponseEntity<Object> deleteDoctor(@PathVariable("id") Long id){
        this.userService.deleteDoctor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/getDoctorProfile/{doctorId}")
    public ResponseEntity<DoctorProfileDTO> getDoctorProfile(@PathVariable ("doctorId") Long doctorId){
        return new ResponseEntity<>(this.userService.getDoctorProfile(doctorId), HttpStatus.OK);
    }

    @GetMapping("/getPatientProfile/{patientId}")
    public ResponseEntity<PatientProfileDTO> getPatientProfile(@PathVariable ("patientId") Long patientId){
        return new ResponseEntity<>(this.userService.getPatientProfile(patientId), HttpStatus.OK);
    }

    @GetMapping("/getPatientBio/{patientId}")
    public ResponseEntity<PatientBioDTO> getPatientBio(@PathVariable ("patientId") Long patientId){
        return new ResponseEntity<>(this.userService.getPatientBio(patientId), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('DOCTOR')")
    @PutMapping("/updateDoctorProfile/{doctorId}")
    public ResponseEntity<UpdatedDoctorProfileDTO> updateDoctorProfile(@PathVariable("doctorId") Long doctorId,
                                                                       @RequestParam(value="profileImage",required = false) MultipartFile profileImage,
                                                                       @RequestParam("university") String university,
                                                                       @RequestParam("description") String description){
        return new ResponseEntity<>(this.userService.updateDoctorProfile(doctorId, profileImage, university, description), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('PATIENT')")
    @PutMapping("/updatePatientProfile/{patientId}")
    public ResponseEntity<Object> updatePatientProfile(@PathVariable("patientId") Long patientId,
                                                       @RequestParam("firstName") String firstName,
                                                       @RequestParam("lastName") String lastName,
                                                       @RequestParam("height") String heightStr,
                                                       @RequestParam("weight") String weightStr,
                                                       @RequestParam("bloodType") String bloodType,
                                                       @RequestParam("phone") String phone,
                                                       @RequestParam("birthDate") LocalDate birthDate,
                                                       @RequestParam(value="profileImage",required = false) MultipartFile profileImage){

        Integer height = heightStr.equals("") ? null : Integer.valueOf(heightStr);
        Integer weight = weightStr.equals("") ? null : Integer.valueOf(weightStr);
        this.userService.updatePatientProfile(patientId,firstName,lastName,height,weight,bloodType,phone,birthDate,profileImage);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/getSearchedDoctors")
    public ResponseEntity<List<DoctorSearchDTO>> getSearchedDoctors(@Valid @RequestBody SearchDataRequestDTO data){
        Long specialityId = data.getSpecialityId();
        String name = Objects.equals(data.getName(), "") ? null : data.getName();
        return new ResponseEntity<>(this.userService.getSearchedDoctors(name, specialityId), HttpStatus.OK);
    }

}
