package com.hmss.springbootserver.controllers;

import com.hmss.springbootserver.DTOs.LoginRequestDTO;
import com.hmss.springbootserver.services.ProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/procedures")
public class ProcedureController {
    private final ProcedureService procedureService;

    @Autowired
    public ProcedureController(ProcedureService procedureService) {
        this.procedureService = procedureService;
    }
    @GetMapping("/getProceduresIdsByHospitalId/{hospitalId}")
    @CrossOrigin(origins = "*")
    public List<Long> getProceduresIdsByHospitalId(@PathVariable("hospitalId") Long hospitalId){
        return this.procedureService.getProceduresIdsByHospitalId(hospitalId);
    }

}
