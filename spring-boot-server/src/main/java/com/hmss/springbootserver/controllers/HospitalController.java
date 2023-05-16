package com.hmss.springbootserver.controllers;

import com.hmss.springbootserver.services.HospitalService;
import org.springframework.http.ResponseEntity;
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
    @CrossOrigin(origins = "*")
    public List<String> getAllHospitalCounties(){
        return this.hospitalService.getAllHospitalCounties();
    }

    @PostMapping("/saveHospitalProcedures/{hospitalId}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Object> saveHospitalProcedures(@PathVariable("hospitalId") Long hospitalId, @RequestBody List<Long> proceduresIds){
        return this.hospitalService.saveHospitalProcedures(hospitalId ,proceduresIds);
    }
}
