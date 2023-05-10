package com.hmss.springbootserver.controllers;

import com.hmss.springbootserver.DTOs.hospital.HospitalWithDoctorsDTO;
import com.hmss.springbootserver.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(path = "api/appointments")
public class AppointmentController {
    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping("/getHospitalsAndDoctorsRecommendations/{counties}/{procedureId}")
    @CrossOrigin(origins = "*")
    public List<HospitalWithDoctorsDTO> getHospitalsAndDoctorsRecommendations(@PathVariable("counties") List<String> counties, @PathVariable("procedureId") long procedureId){
        return this.appointmentService.getHospitalsAndDoctorsRecommendations(counties,procedureId);
    }
    @GetMapping("/getAvailableAppointments/{ids}/{procedureId}/{startDate}/{endDate}")
    @CrossOrigin(origins = "*")
    public Object getAvailableAppointments(@PathVariable("ids") List<Long> ids,
                                           @PathVariable("procedureId") long procedureId,
                                           @PathVariable("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
                                           @PathVariable("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        System.out.println(startDate);
        System.out.println(endDate);
        var x = this.appointmentService.getAvailableAppointments(ids,procedureId,startDate,endDate);
        return x;
    }
}
