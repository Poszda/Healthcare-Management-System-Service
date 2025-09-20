package com.hmss.springbootserver.services;

import com.hmss.springbootserver.DTOs.user.LoginRequestDTO;
import com.hmss.springbootserver.DTOs.user.SignUpRequestDTO;
import com.hmss.springbootserver.DTOs.user.AdminLoginDTO;
import com.hmss.springbootserver.DTOs.user.DoctorLoginDTO;
import com.hmss.springbootserver.DTOs.user.PatientLoginDTO;
import com.hmss.springbootserver.exceptions.EmailAlreadyInUseException;
import com.hmss.springbootserver.exceptions.IncorrectPasswordException;
import com.hmss.springbootserver.exceptions.PasswordsNotMatchingException;
import com.hmss.springbootserver.exceptions.ResourceNotFoundException;
import com.hmss.springbootserver.security.JwtService;
import com.hmss.springbootserver.entities.Patient;
import com.hmss.springbootserver.entities.User;
import com.hmss.springbootserver.enums.UserType;
import com.hmss.springbootserver.mappers.AdminMapper;
import com.hmss.springbootserver.mappers.DoctorMapper;
import com.hmss.springbootserver.mappers.PatientMapper;
import com.hmss.springbootserver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Object login(LoginRequestDTO loginRequest){
        Optional<User> user = userRepository.findByEmail(loginRequest.getEmail());
        if (user.isPresent()){
            User foundUser = user.get();
            if (passwordEncoder.matches(loginRequest.getPassword(), foundUser.getPassword())){
                if (foundUser.getUserType() == UserType.PATIENT) {
                    PatientLoginDTO patient = new PatientLoginDTO(
                            foundUser.getId(),
                            foundUser.getFirstName(),
                            foundUser.getLastName(),
                            foundUser.getEmail(),
                            foundUser.getUserType(),
                            jwtService.generateToken(foundUser.getEmail()),
                            PatientMapper.INSTANCE.toPatientDTO(foundUser.getPatient())
                    );
                    return patient;
                }
                else if (foundUser.getUserType() == UserType.DOCTOR) {
                    DoctorLoginDTO doctor = new DoctorLoginDTO(
                            foundUser.getId(),
                            foundUser.getFirstName(),
                            foundUser.getLastName(),
                            foundUser.getEmail(),
                            foundUser.getUserType(),
                            jwtService.generateToken(foundUser.getEmail()),
                            DoctorMapper.INSTANCE.toDoctorDTO(foundUser.getDoctor())
                    );
                    return doctor;
                }
                else if (foundUser.getUserType() == UserType.ADMIN) {
                    AdminLoginDTO admin = new AdminLoginDTO(
                            foundUser.getId(),
                            foundUser.getFirstName(),
                            foundUser.getLastName(),
                            foundUser.getEmail(),
                            foundUser.getUserType(),
                            jwtService.generateToken(foundUser.getEmail()),
                            AdminMapper.INSTANCE.toAdminWithHospitalDTO(foundUser.getAdmin())
                    );
                    return admin;
                }
                else{
                    throw new ResourceNotFoundException("User not found");
                }
            }
            else{
                throw new IncorrectPasswordException("Incorrect password");
            }
        }
        else{
            throw new ResourceNotFoundException("User not found");
        }
    }

    public void signUp(SignUpRequestDTO signUpRequest){
        if(this.userRepository.findByEmail(signUpRequest.getEmail()).isPresent()){
            throw new EmailAlreadyInUseException("Email already in use");
        }
        if(!Objects.equals(signUpRequest.getPassword(),signUpRequest.getRePassword())){
            throw new PasswordsNotMatchingException("Passwords does not match");
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
    }
}
