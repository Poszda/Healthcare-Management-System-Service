package com.hmss.springbootserver.services;

import com.hmss.springbootserver.DTOs.LoginRequestDTO;
import com.hmss.springbootserver.DTOs.SignUpRequestDTO;
import com.hmss.springbootserver.entities.Patient;
import com.hmss.springbootserver.entities.User;
import com.hmss.springbootserver.enums.UserType;
import com.hmss.springbootserver.mappers.UserMapper;
import com.hmss.springbootserver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class AuthService {
    private final UserRepository userRepository;

    @Autowired
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<Object> login(LoginRequestDTO loginRequest){
        Optional<User> user = userRepository.findByEmail(loginRequest.getEmail());
        if (user.isPresent()){
            User foundUser = user.get();
            if(Objects.equals(foundUser.getPassword(), loginRequest.getPassword())){
                if(foundUser.getUserType() == UserType.PATIENT)
                    return new ResponseEntity<>(UserMapper.INSTANCE.UserToPatientLoginDTO(foundUser),HttpStatus.OK);
                else if (foundUser.getUserType() == UserType.DOCTOR)
                    return new ResponseEntity<>(UserMapper.INSTANCE.UserToDoctorLoginDTO(foundUser),HttpStatus.OK);
                else if (foundUser.getUserType() == UserType.ADMIN)
                    return new ResponseEntity<>(UserMapper.INSTANCE.UserToAdminLoginDTO(foundUser),HttpStatus.OK);
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
        user.setPassword(signUpRequest.getPassword());
        user.setUserType(UserType.PATIENT);
        user.setPatient(patient);
        patient.setBirthDate(signUpRequest.getBirthDate());
        patient.setUser(user);
        User createdUser = userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
