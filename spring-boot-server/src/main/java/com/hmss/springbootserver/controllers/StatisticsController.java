package com.hmss.springbootserver.controllers;

import com.hmss.springbootserver.DTOs.appointments.TodayProgramDTO;
import com.hmss.springbootserver.DTOs.statistics.*;
import com.hmss.springbootserver.services.StatisticsService;
import com.hmss.springbootserver.utils.models.projections.SpecialityFrequencyProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/statistics")
public class StatisticsController {

    private final StatisticsService statisticsService;

    @Autowired
    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @PreAuthorize("hasRole('DOCTOR')")
    @GetMapping("/getTodayProgram/{doctorId}")
    public ResponseEntity<TodayProgramDTO> getTodayProgram(@PathVariable Long doctorId){
        return new ResponseEntity<>(this.statisticsService.getTodayProgram(doctorId), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getHospitalOverview/{hospitalId}")
    public ResponseEntity<HospitalOverviewDTO> getHospitalOverview(@PathVariable Long hospitalId){
        return new ResponseEntity<>(this.statisticsService.getHospitalOverview(hospitalId), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getHospitalPeriodicStats/{hospitalId}")
    public ResponseEntity<List<HospitalMonthStatisticDTO>> getHospitalPeriodicStats(@PathVariable Long hospitalId){
        return new ResponseEntity<>(this.statisticsService.getHospitalPeriodicStats(hospitalId), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getHospitalSpecialityFrequency/{hospitalId}")
    public ResponseEntity<List<SpecialityFrequencyProjection>> getHospitalSpecialityFrequency(@PathVariable Long hospitalId){
        return new ResponseEntity<>(this.statisticsService.getHospitalSpecialityFrequency(hospitalId), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('DOCTOR')")
    @GetMapping("/getDoctorPatientsVisitsByAgeGroup/{doctorId}")
    public ResponseEntity<List<DoctorPatientsVisitsByAgeGroupDTO>> getDoctorPatientsVisitsByAgeGroup(@PathVariable Long doctorId){
        return new ResponseEntity<>(this.statisticsService.getDoctorPatientsVisitsByAgeGroup(doctorId), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('DOCTOR')")
    @GetMapping("/getDoctorAppointmentsCountsByStatus/{doctorId}")
    public ResponseEntity<List<DoctorAppointmentsCounterByStatusDTO>> getDoctorAppointmentsCountsByStatus(@PathVariable Long doctorId){
        return new ResponseEntity<>(this.statisticsService.getDoctorAppointmentsCountsByStatus(doctorId), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('DOCTOR')")
    @GetMapping("/getDoctorInterventionsByCountProcedure/{doctorId}")
    public ResponseEntity<List<DoctorInterventionsCountByProcedureDTO>> getDoctorInterventionsByCountProcedure(@PathVariable Long doctorId){
        return new ResponseEntity<>(this.statisticsService.getDoctorInterventionsByCountProcedure(doctorId), HttpStatus.OK);
    }

}
