package com.hmss.springbootserver.controllers;

import com.hmss.springbootserver.entities.Patient;
import com.hmss.springbootserver.entities.User;
import com.hmss.springbootserver.repositories.PatientRepository;
import com.hmss.springbootserver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PatientRepository patientRepository;

    @Autowired
    public AuthController(UserRepository userRepository, PatientRepository patientRepository) {
        this.userRepository = userRepository;
        this.patientRepository = patientRepository;
    }


    @GetMapping("/users")
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/patients")
    public List<Patient> getPatients(){
        return patientRepository.findAll();
    }
}
