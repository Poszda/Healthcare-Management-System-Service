package com.hmss.springbootserver.controllers;

import com.hmss.springbootserver.DTOs.AvailableAppointmentsRequestDTO;
import com.hmss.springbootserver.DTOs.hospital.HospitalWithDoctorsDTO;
import com.hmss.springbootserver.entities.Patient;
import com.hmss.springbootserver.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    @GetMapping("/getAvailableAppointments/{ids}/{startDate}/{endDate}")
    @CrossOrigin(origins = "*")
    public Object getAvailableAppointments(@PathVariable("ids") List<Long> ids,
                                           @PathVariable("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
                                           @PathVariable("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        System.out.println(startDate);
        System.out.println(endDate);
        return null;
        //@RequestParam List<Long> ids,
    }
}
