package com.hmss.springbootserver.controllers;

import com.hmss.springbootserver.services.HospitalService;
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
    public List<String> getAllHospitalCounties(){
        return this.hospitalService.getAllHospitalCounties();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/saveHospitalProcedures/{hospitalId}")
    public ResponseEntity<Object> saveHospitalProcedures(@PathVariable("hospitalId") Long hospitalId, @RequestBody List<Long> proceduresIds){
        return this.hospitalService.saveHospitalProcedures(hospitalId ,proceduresIds);
    }
}
