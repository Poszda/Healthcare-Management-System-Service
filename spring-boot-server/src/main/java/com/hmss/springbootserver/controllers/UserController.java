package com.hmss.springbootserver.controllers;

import com.hmss.springbootserver.DTOs.doctor.*;
import com.hmss.springbootserver.DTOs.patient.UpdatePatientProfileRequest;
import com.hmss.springbootserver.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getDoctorsById/{ids}")
    @CrossOrigin(origins = "*")
    public List<DoctorDTO> getDoctorsById(@PathVariable ("ids") List<Long> ids){
        return this.userService.getDoctorsById(ids);
    }

    @GetMapping("/getDoctorsWithUsersAndHospitalsById/{ids}")
    @CrossOrigin(origins = "*")
    public List<DoctorWithUserAndHospitalDTO> getDoctorsWithUsersAndHospitalsById(@PathVariable ("ids") List<Long> ids){
        return this.userService.getDoctorsWithUsersAndHospitalsById(ids);
    }

    @GetMapping("/getHospitalDoctorsWithSpeciality/{hospitalId}")
    @CrossOrigin(origins = "*")
    public List<DoctorWithUserAndSpecialityDTO> getHospitalDoctorsWithSpeciality(@PathVariable ("hospitalId") Long hospitalId){
        return this.userService.getHospitalDoctorsWithSpeciality(hospitalId);
    }

    @PostMapping("/createDoctor")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Object> createDoctor(@RequestBody CreateDoctorRequestDTO createDoctorRequest){
        return this.userService.createDoctor(createDoctorRequest);
    }

    @DeleteMapping("/deleteDoctor/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Object> deleteDoctor(@PathVariable("id") Long id){
        return this.userService.deleteDoctor(id);
    }

    @GetMapping("/getDoctorProfile/{doctorId}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Object> getDoctorProfile(@PathVariable ("doctorId") Long doctorId){
        return this.userService.getDoctorProfile(doctorId);
    }

    @GetMapping("/getPatientProfile/{patientId}")
    @CrossOrigin(origins = "*")
    public Object getPatientProfile(@PathVariable ("patientId") Long patientId){
        return this.userService.getPatientProfile(patientId);
    }
    @GetMapping("/getPatient/{patientId}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Object> getPatient(@PathVariable ("patientId") Long patientId){
        return this.userService.getPatient(patientId);
    }

    @PutMapping("/updateDoctorUniversityAndDescription/{doctorId}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Object> updateDoctorUniversityAndDescription(@PathVariable("doctorId") Long doctorId,@RequestBody UpdateDoctorUniversityAndDescriptionRequest request){
        return this.userService.updateDoctorUniversityAndDescription(doctorId,request);
    }

    @PutMapping("/updatePatientProfile/{patientId}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Object> updatePatientProfile(@PathVariable("patientId") Long patientId,@RequestBody UpdatePatientProfileRequest request){
        return this.userService.updatePatientProfile(patientId,request);
    }

    @PostMapping("/getSearchedDoctors")
    @CrossOrigin(origins = "*")
    public List<DoctorWithUserDTO> getSearchedDoctors(@RequestBody SearchDataRequest data){
        String name = data.getName();
        Long specialityId = data.getSpecialityId();
        if(data.getName() == "") name = null;
        return this.userService.getSearchedDoctors(name,specialityId);
    }


}
