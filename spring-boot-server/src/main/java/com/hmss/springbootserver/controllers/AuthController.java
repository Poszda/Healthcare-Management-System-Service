package com.hmss.springbootserver.controllers;

import com.hmss.springbootserver.DTOs.AppointmentDTO;
import com.hmss.springbootserver.DTOs.PatientDTO;
import com.hmss.springbootserver.DTOs.UserDTO;
import com.hmss.springbootserver.entities.Appointment;
import com.hmss.springbootserver.entities.Patient;
import com.hmss.springbootserver.entities.User;
import com.hmss.springbootserver.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/auth")
public class AuthController {

    private final AuthService authService;
    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return this.authService.getAllUsers();
    }
    @GetMapping("/patients")
    public List<Patient> getAllPatients(){
        return this.authService.getAllPatients();
    }
    @GetMapping("/appointments")
    public List<Appointment> getAllAppointments(){
        return this.authService.getAllAppointments();
    }

    @GetMapping("/createUserWithPatient")
    public boolean createUserWithPatient(){
        return this.authService.createUserWithPatient();
    }

    @GetMapping("/createUser")
    public boolean createUser(){
        return this.authService.createUser();
    }
    @GetMapping("/getFirstUser")
    public UserDTO getFirstUser() {
        return this.authService.getFirstUser();
    }

    @GetMapping("/getFirstPatient")
    public PatientDTO getFirstPatient() {
        return this.authService.getFirstPatient();
    }

    @GetMapping("/getFirstUserRestricted")
    public UserDTO getFirstUserRestricted() {
        return this.authService.getFirstUserRestricted();
    }

    @GetMapping("/getFirstPatientRestricted")
    public PatientDTO getFirstPatientRestricted() {
        return this.authService.getFirstPatientRestricted();
    }
    @GetMapping("/getCustomUser")
    public UserDTO getCustomUser(){
        return this.authService.getCustomUser();
    }
    @GetMapping("/getFirstAppointment")
    public AppointmentDTO getFirstAppointment(){
        return this.authService.getFirstAppointment();
    }
    @GetMapping("/getFirstAppointmentRestricted")
    public AppointmentDTO getFirstAppointmentRestricted(){
        return this.authService.getFirstAppointmentRestricted();
    }
}
