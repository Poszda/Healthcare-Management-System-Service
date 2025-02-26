package com.hmss.springbootserver.services;

import com.hmss.springbootserver.DTOs.user.LoginRequestDTO;
import com.hmss.springbootserver.DTOs.user.SignUpRequestDTO;
import com.hmss.springbootserver.DTOs.user.AdminLoginDTO;
import com.hmss.springbootserver.DTOs.user.DoctorLoginDTO;
import com.hmss.springbootserver.DTOs.user.PatientLoginDTO;
import com.hmss.springbootserver.security.JwtService;
import com.hmss.springbootserver.entities.Patient;
import com.hmss.springbootserver.entities.User;
import com.hmss.springbootserver.enums.UserType;
import com.hmss.springbootserver.mappers.AdminMapper;
import com.hmss.springbootserver.mappers.DoctorMapper;
import com.hmss.springbootserver.mappers.PatientMapper;
import com.hmss.springbootserver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Objects;
import java.util.Optional;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    @Autowired
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public ResponseEntity<Object> login(LoginRequestDTO loginRequest){
        Optional<User> user = userRepository.findByEmail(loginRequest.getEmail());
        if (user.isPresent()){
            User foundUser = user.get();
            if (passwordEncoder.matches(loginRequest.getPassword(), foundUser.getPassword())){
                if (foundUser.getUserType() == UserType.PATIENT) {
                    var x = new PatientLoginDTO(
                            foundUser.getId(),
                            foundUser.getFirstName(),
                            foundUser.getLastName(),
                            foundUser.getEmail(),
                            foundUser.getUserType(),
                            jwtService.generateToken(foundUser.getEmail()),
                            PatientMapper.INSTANCE.toPatientDTO(foundUser.getPatient())
                    );
                    return new ResponseEntity<>(x, HttpStatus.OK);
                }
                else if (foundUser.getUserType() == UserType.DOCTOR) {
                    var x = new DoctorLoginDTO(
                            foundUser.getId(),
                            foundUser.getFirstName(),
                            foundUser.getLastName(),
                            foundUser.getEmail(),
                            foundUser.getUserType(),
                            jwtService.generateToken(foundUser.getEmail()),
                            DoctorMapper.INSTANCE.toDoctorDTO(foundUser.getDoctor())
                    );
                    return new ResponseEntity<>(x, HttpStatus.OK);
                }
                else if (foundUser.getUserType() == UserType.ADMIN) {
                    var x = new AdminLoginDTO(
                            foundUser.getId(),
                            foundUser.getFirstName(),
                            foundUser.getLastName(),
                            foundUser.getEmail(),
                            foundUser.getUserType(),
                            jwtService.generateToken(foundUser.getEmail()),
                            AdminMapper.INSTANCE.toAdminWithHospitalDTO(foundUser.getAdmin())
                    );
                    return new ResponseEntity<>(x, HttpStatus.OK);
                }
                else{
                    return new ResponseEntity<>("User not found",HttpStatus.NOT_FOUND);
                }
            }
            else{
                return new ResponseEntity<>("Incorrect password",HttpStatus.UNAUTHORIZED);
            }
        }
        else{
            return new ResponseEntity<>("User not found",HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Object> signUp(SignUpRequestDTO signUpRequest){
        if(this.userRepository.findByEmail(signUpRequest.getEmail()).isPresent()){
            return new ResponseEntity<>("Email already exist",HttpStatus.CONFLICT);
        }
        if(!Objects.equals(signUpRequest.getPassword(),signUpRequest.getRePassword())){
            return new ResponseEntity<>("Passwords does not match",HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        Patient patient = new Patient();
        user.setEmail(signUpRequest.getEmail());
        user.setFirstName(signUpRequest.getFirstName());
        user.setLastName(signUpRequest.getLastName());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()) );
        user.setUserType(UserType.PATIENT);
        user.setPatient(patient);
        patient.setBirthDate(signUpRequest.getBirthDate());
        patient.setUser(user);

        userRepository.save(user);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
