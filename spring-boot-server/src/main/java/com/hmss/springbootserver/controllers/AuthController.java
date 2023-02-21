package com.hmss.springbootserver.controllers;

import com.hmss.springbootserver.DTOs.LoginRequestDTO;
import com.hmss.springbootserver.entities.Appointment;
import com.hmss.springbootserver.entities.Patient;
import com.hmss.springbootserver.entities.User;
import com.hmss.springbootserver.mappers.UserMapper;
import com.hmss.springbootserver.repositories.AppointmentRepository;
import com.hmss.springbootserver.repositories.PatientRepository;
import com.hmss.springbootserver.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PatientRepository patientRepository;

    private final AppointmentRepository appointmentRepository;

    @Autowired
    public AuthController(UserRepository userRepository, PatientRepository patientRepository, AppointmentRepository appointmentRepository) {
        this.userRepository = userRepository;
        this.patientRepository = patientRepository;
        this.appointmentRepository = appointmentRepository;
    }


    @GetMapping("/users")
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/patients")
    public List<Patient> getPatients(){
        var x = patientRepository.findAll();
        return x;
    }

    @GetMapping("/appointments")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Appointment> getAppointments(){
        var x = appointmentRepository.findAll();
        return x;
    }
    @PostMapping("/login")
    @Transactional
    @CrossOrigin(origins = "http://localhost:4200")
    public Object login(@RequestBody LoginRequestDTO loginRequest){
        User user = userRepository.findByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword());
        if(user != null){
//            if(user.getType() == 1){
//                return user.getAdmin();
//            }
//            if(user.getType() == 2){
//                user.getPatient();
//            }
//            if(user.getType() == 3){
//                var x = user.getPatient();
//                return x;
//            }
            return UserMapper.toUserDTONoPassword(user);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
