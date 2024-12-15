package com.hmss.springbootserver.controllers;

import com.hmss.springbootserver.DTOs.statistics.*;
import com.hmss.springbootserver.services.StatisticsService;
import com.hmss.springbootserver.utils.models.projections.SpecialityFrequencyProjection;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/getTodayProgram/{doctorId}")
    @CrossOrigin(origins = "*")
    public Object getTodayProgram(@PathVariable Long doctorId){
        return this.statisticsService.getTodayProgram(doctorId);
    }

    @GetMapping("/getHospitalOverview/{hospitalId}")
    @CrossOrigin(origins = "*")
    public HospitalOverviewDTO getHospitalOverview(@PathVariable Long hospitalId){
        return this.statisticsService.getHospitalOverview(hospitalId);
    }

    @GetMapping("/getHospitalPeriodicStats/{hospitalId}")
    @CrossOrigin(origins = "*")
    public List<HospitalMonthStatisticDTO> getHospitalPeriodicStats(@PathVariable Long hospitalId){
        return this.statisticsService.getHospitalPeriodicStats(hospitalId);
    }

    @GetMapping("/getHospitalSpecialityFrequency/{hospitalId}")
    @CrossOrigin(origins = "*")
    public List<SpecialityFrequencyProjection> getHospitalSpecialityFrequency(@PathVariable Long hospitalId){
        return this.statisticsService.getHospitalSpecialityFrequency(hospitalId);
    }

    @GetMapping("/getDoctorPatientsVisitsByAgeGroup/{doctorId}")
    @CrossOrigin(origins = "*")
    public List<DoctorPatientsVisitsByAgeGroupDTO> getDoctorPatientsVisitsByAgeGroup(@PathVariable Long doctorId){
        return this.statisticsService.getDoctorPatientsVisitsByAgeGroup(doctorId);
    }

    @GetMapping("/getDoctorAppointmentsCountsByStatus/{doctorId}")
    @CrossOrigin(origins = "*")
    public List<DoctorAppointmentsCounterByStatusDTO> getDoctorAppointmentsCountsByStatus(@PathVariable Long doctorId){
        return this.statisticsService.getDoctorAppointmentsCountsByStatus(doctorId);
    }

    @GetMapping("/getDoctorInterventionsByCountProcedure/{doctorId}")
    @CrossOrigin(origins = "*")
    public List<DoctorInterventionsCountByProcedureDTO> getDoctorInterventionsByCountProcedure(@PathVariable Long doctorId){
        return this.statisticsService.getDoctorInterventionsByCountProcedure(doctorId);
    }

}
