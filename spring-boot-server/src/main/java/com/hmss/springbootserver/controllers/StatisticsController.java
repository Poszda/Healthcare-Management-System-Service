package com.hmss.springbootserver.controllers;

import com.hmss.springbootserver.DTOs.speciality.SpecialityWithProceduresDTO;
import com.hmss.springbootserver.DTOs.statistics.HospitalMonthStatisticDTO;
import com.hmss.springbootserver.DTOs.statistics.HospitalOverviewDTO;
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
    public Object getTodayProgram(){
        return this.statisticsService.getTodayProgram();
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
}
