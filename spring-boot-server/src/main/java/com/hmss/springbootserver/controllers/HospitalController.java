package com.hmss.springbootserver.controllers;

import com.hmss.springbootserver.services.HospitalService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
