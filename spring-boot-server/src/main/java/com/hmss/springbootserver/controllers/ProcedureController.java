package com.hmss.springbootserver.controllers;

import com.hmss.springbootserver.services.HospitalService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/procedures")
public class ProceduresController {
    private final Procedr hospitalService;
}
