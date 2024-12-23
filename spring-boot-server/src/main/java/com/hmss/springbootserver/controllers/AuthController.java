package com.hmss.springbootserver.controllers;

import com.hmss.springbootserver.DTOs.LoginRequestDTO;
import com.hmss.springbootserver.DTOs.SignUpRequestDTO;
import com.hmss.springbootserver.repositories.UserRepository;
import com.hmss.springbootserver.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @CrossOrigin(origins = "*")
    public ResponseEntity<Object> login(@RequestBody LoginRequestDTO loginRequest){
        return this.authService.login(loginRequest);
    }

    @PostMapping("/signup")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Object> signUp(@RequestBody SignUpRequestDTO signUpRequest){
        return this.authService.signUp(signUpRequest);
    }
}
