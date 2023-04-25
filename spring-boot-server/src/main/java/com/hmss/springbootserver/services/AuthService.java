package com.hmss.springbootserver.services;

import com.hmss.springbootserver.DTOs.LoginRequestDTO;
import com.hmss.springbootserver.entities.User;
import com.hmss.springbootserver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

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
                return new ResponseEntity<>(HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>("Incorrect password",HttpStatus.UNAUTHORIZED);
            }
        }
        else{
            return new ResponseEntity<>("User not found",HttpStatus.NOT_FOUND);
        }
        //return null;
    }
}
