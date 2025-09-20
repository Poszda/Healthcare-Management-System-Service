package com.hmss.springbootserver.controllers;

import com.hmss.springbootserver.services.HospitalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/hospitals")
public class HospitalController {

    private final HospitalService hospitalService;

    public HospitalController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    @GetMapping("/getAllHospitalCounties")
    public ResponseEntity<List<String>> getAllHospitalCounties(){
        return new ResponseEntity<>(this.hospitalService.getAllHospitalCounties(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/saveHospitalProcedures/{hospitalId}")
    public ResponseEntity<Object> saveHospitalProcedures(@PathVariable("hospitalId") Long hospitalId, @RequestBody List<Long> proceduresIds){
        this.hospitalService.saveHospitalProcedures(hospitalId ,proceduresIds);
        return new ResponseEntity<>("Changes made successfully", HttpStatus.OK);
    }
}
