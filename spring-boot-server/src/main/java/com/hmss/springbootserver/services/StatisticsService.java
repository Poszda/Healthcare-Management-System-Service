package com.hmss.springbootserver.services;

import com.hmss.springbootserver.DTOs.statistics.HospitalMonthStatisticDTO;
import com.hmss.springbootserver.DTOs.statistics.HospitalOverviewDTO;
import com.hmss.springbootserver.repositories.AppointmentRepository;
import com.hmss.springbootserver.repositories.HospitalRepository;
import com.hmss.springbootserver.utils.models.AppointmentSimplified;
import com.hmss.springbootserver.utils.models.projections.AppointmentStatisticProjection;
import com.hmss.springbootserver.utils.models.projections.SpecialityFrequencyProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

@Service
public class StatisticsService {

    private final AppointmentRepository appointmentRepository;
    private final HospitalRepository hospitalRepository;

    @Autowired
    public StatisticsService(AppointmentRepository appointmentRepository,
                             HospitalRepository hospitalRepository) {
        this.appointmentRepository = appointmentRepository;
        this.hospitalRepository = hospitalRepository;
    }

    public Object getTodayProgram() {
        return null;
    }

    public List<HospitalMonthStatisticDTO> getHospitalPeriodicStats(Long hospitalId) {
        LocalDate fiveMonthsAgo = LocalDate.now().minusMonths(5).withDayOfMonth(1);
        List<AppointmentStatisticProjection> list = this.hospitalRepository.findAppointmentsLast6Months(hospitalId,fiveMonthsAgo);
        List<HospitalMonthStatisticDTO> periodicStatistics = new ArrayList<>();

        LocalDate x = LocalDate.now().minusMonths(5);
        for(int i=0;i<6;i++){
            String month = x.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault());
            var stat = list.stream().filter(el -> el.getMonth().equals(month)).findFirst();
            if(stat.isPresent()){
                periodicStatistics.add(new HospitalMonthStatisticDTO(
                        month,
                        stat.get().getAppointments(),
                        stat.get().getTotal(),
                        i
                ));
            }
            else{
                periodicStatistics.add(new HospitalMonthStatisticDTO(month, 0, 0, i));
            }
            x = x.plusMonths(1);
        }
        return periodicStatistics;
    }

    public List<SpecialityFrequencyProjection> getHospitalSpecialityFrequency(Long hospitalId) {
        int month = LocalDate.now().getMonthValue();
        return this.hospitalRepository.findPatientVisitBySpeciality(hospitalId,month);
    }

    public HospitalOverviewDTO getHospitalOverview(Long hospitalId) {
        int month = LocalDate.now().getMonthValue();
        Long appointments = this.hospitalRepository.findAppointmentsNumberByMonth(hospitalId,month);
        Double earnings = this.hospitalRepository.findEstimatedEarningsByMonth(hospitalId,month);
        Double avgAge = this.hospitalRepository.findAveragePatientAgeByMonth(hospitalId,month);

        return new HospitalOverviewDTO(
                appointments != null?appointments:0,
                earnings != null ?earnings :0,
                avgAge != null?avgAge :0
        );
    }
}
