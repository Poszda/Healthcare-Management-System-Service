package com.hmss.springbootserver.controllers;

import com.hmss.springbootserver.DTOs.SignUpRequestDTO;
import com.hmss.springbootserver.DTOs.doctor.CreateDoctorRequestDTO;
import com.hmss.springbootserver.DTOs.doctor.DoctorDTO;
import com.hmss.springbootserver.DTOs.doctor.DoctorWithUserAndHospitalDTO;
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

    @PostMapping("/createDoctor")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Object> createDoctor(@RequestBody CreateDoctorRequestDTO createDoctorRequest){
        return this.userService.createDoctor(createDoctorRequest);
    }
}
