package com.hmss.springbootserver.controllers;

import com.hmss.springbootserver.DTOs.user.LoginRequestDTO;
import com.hmss.springbootserver.DTOs.user.SignUpRequestDTO;
import com.hmss.springbootserver.repositories.UserRepository;
import com.hmss.springbootserver.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/auth")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService,
                          UserRepository userRepository) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@Valid @RequestBody LoginRequestDTO loginRequest){
        Object userDTO = this.authService.login(loginRequest);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> signUp(@Valid @RequestBody SignUpRequestDTO signUpRequest){
        this.authService.signUp(signUpRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
