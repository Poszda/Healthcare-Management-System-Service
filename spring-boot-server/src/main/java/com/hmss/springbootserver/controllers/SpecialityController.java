package com.hmss.springbootserver.controllers;

import com.hmss.springbootserver.DTOs.speciality.SpecialityDTO;
import com.hmss.springbootserver.DTOs.speciality.SpecialityWithProceduresDTO;
import com.hmss.springbootserver.services.SpecialityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/specialities")
public class SpecialityController {
    private final SpecialityService specialityService;

    @Autowired
    public SpecialityController(SpecialityService specialityService) {
        this.specialityService = specialityService;
    }

    @GetMapping("/getSpecialities")
    public ResponseEntity<List<SpecialityDTO>> getSpecialities(Authentication authentication) {
//        var x = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
        return new ResponseEntity<>(this.specialityService.getSpecialities(), HttpStatus.OK);
    }

    @GetMapping("/getSpecialitiesWithProcedures")
    public ResponseEntity<List<SpecialityWithProceduresDTO>> getSpecialitiesWithProcedures() {
        return new ResponseEntity<>(this.specialityService.getSpecialitiesWithProcedures(), HttpStatus.OK);
    }

    @GetMapping("/getHospitalSpecialities/{hospitalId}")
    public ResponseEntity<List<SpecialityDTO>> getHospitalSpecialities(@PathVariable Long hospitalId) {
        return new ResponseEntity<>(this.specialityService.getHospitalSpecialities(hospitalId), HttpStatus.OK);
    }

}
