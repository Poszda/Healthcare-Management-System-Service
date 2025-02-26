package com.hmss.springbootserver.controllers;

import com.hmss.springbootserver.DTOs.speciality.SpecialityDTO;
import com.hmss.springbootserver.DTOs.speciality.SpecialityWithProceduresDTO;
import com.hmss.springbootserver.services.SpecialityService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<SpecialityDTO> getSpecialities(Authentication authentication) {
        var x = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
        return this.specialityService.getSpecialities();
    }

    @GetMapping("/getSpecialitiesWithProcedures")
    public List<SpecialityWithProceduresDTO> getSpecialitiesWithProcedures() {
        return this.specialityService.getSpecialitiesWithProcedures();
    }

    @GetMapping("/getHospitalSpecialities/{hospitalId}")
    public List<SpecialityDTO> getHospitalSpecialities(@PathVariable Long hospitalId) {
        return this.specialityService.getHospitalSpecialities(hospitalId);
    }
}
