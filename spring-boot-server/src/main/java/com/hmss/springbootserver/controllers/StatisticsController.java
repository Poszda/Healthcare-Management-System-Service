package com.hmss.springbootserver.controllers;

import com.hmss.springbootserver.DTOs.statistics.*;
import com.hmss.springbootserver.services.StatisticsService;
import com.hmss.springbootserver.utils.models.projections.SpecialityFrequencyProjection;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Object getTodayProgram(@PathVariable Long doctorId){
        return this.statisticsService.getTodayProgram(doctorId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getHospitalOverview/{hospitalId}")
    public HospitalOverviewDTO getHospitalOverview(@PathVariable Long hospitalId){
        return this.statisticsService.getHospitalOverview(hospitalId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getHospitalPeriodicStats/{hospitalId}")
    public List<HospitalMonthStatisticDTO> getHospitalPeriodicStats(@PathVariable Long hospitalId){
        return this.statisticsService.getHospitalPeriodicStats(hospitalId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getHospitalSpecialityFrequency/{hospitalId}")
    public List<SpecialityFrequencyProjection> getHospitalSpecialityFrequency(@PathVariable Long hospitalId){
        return this.statisticsService.getHospitalSpecialityFrequency(hospitalId);
    }

    @PreAuthorize("hasRole('DOCTOR')")
    @GetMapping("/getDoctorPatientsVisitsByAgeGroup/{doctorId}")
    public List<DoctorPatientsVisitsByAgeGroupDTO> getDoctorPatientsVisitsByAgeGroup(@PathVariable Long doctorId){
        return this.statisticsService.getDoctorPatientsVisitsByAgeGroup(doctorId);
    }

    @PreAuthorize("hasRole('DOCTOR')")
    @GetMapping("/getDoctorAppointmentsCountsByStatus/{doctorId}")
    public List<DoctorAppointmentsCounterByStatusDTO> getDoctorAppointmentsCountsByStatus(@PathVariable Long doctorId){
        return this.statisticsService.getDoctorAppointmentsCountsByStatus(doctorId);
    }

    @PreAuthorize("hasRole('DOCTOR')")
    @GetMapping("/getDoctorInterventionsByCountProcedure/{doctorId}")
    public List<DoctorInterventionsCountByProcedureDTO> getDoctorInterventionsByCountProcedure(@PathVariable Long doctorId){
        return this.statisticsService.getDoctorInterventionsByCountProcedure(doctorId);
    }

}
