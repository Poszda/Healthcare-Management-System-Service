package com.hmss.springbootserver.controllers;

import com.hmss.springbootserver.DTOs.doctor.*;
import com.hmss.springbootserver.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    @CrossOrigin(origins = "*")
    public List<DoctorDTO> getDoctorsById(@PathVariable ("ids") List<Long> ids){
        return this.userService.getDoctorsById(ids);
    }

    @GetMapping("/getDoctorsSuggestionInfo/{ids}")
    @CrossOrigin(origins = "*")
    public List<DoctorSuggestionInfoDTO> getDoctorsSuggestionInfo(@PathVariable ("ids") List<Long> ids){
        return this.userService.getDoctorsSuggestionInfo(ids);
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
    public ResponseEntity<Object> getPatientProfile(@PathVariable ("patientId") Long patientId){
        return this.userService.getPatientProfile(patientId);
    }
    @GetMapping("/getPatientBio/{patientId}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Object> getPatientBio(@PathVariable ("patientId") Long patientId){
        return this.userService.getPatientBio(patientId);
    }

    @PutMapping("/updateDoctorProfile/{doctorId}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Object> updateDoctorProfile(@PathVariable("doctorId") Long doctorId,
                                                      @RequestParam(value="profileImage",required = false) MultipartFile profileImage,
                                                      @RequestParam("university") String university,
                                                      @RequestParam("description") String description){
        return this.userService.updateDoctorProfile(doctorId,profileImage,university,description);
    }

    @PutMapping("/updatePatientProfile/{patientId}")
    @CrossOrigin(origins = "*")
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
        return this.userService.updatePatientProfile(patientId,firstName,lastName,height,weight,bloodType,phone,birthDate,profileImage);
    }

    @PostMapping("/getSearchedDoctors")
    @CrossOrigin(origins = "*")
    public List<DoctorSearchDTO> getSearchedDoctors(@RequestBody SearchDataRequest data){
        Long specialityId = data.getSpecialityId();
        String name = Objects.equals(data.getName(), "") ? null:data.getName();
        return this.userService.getSearchedDoctors(name,specialityId);
    }


}
