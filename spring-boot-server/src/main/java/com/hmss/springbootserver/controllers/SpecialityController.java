package com.hmss.springbootserver.controllers;

import com.hmss.springbootserver.DTOs.speciality.SpecialityWithProceduresDTO;
import com.hmss.springbootserver.services.SpecialityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/specialities")
public class SpecialityController {
    private final SpecialityService specialityService;

    @Autowired
    public SpecialityController(SpecialityService specialityService) {
        this.specialityService = specialityService;
    }

    @GetMapping("/getSpecialitiesWithProcedures")
    @CrossOrigin(origins = "*")
    public List<SpecialityWithProceduresDTO> getSpecialitiesWithProcedures(){
        return this.specialityService.getSpecialitiesWithProcedures();
    }
}
