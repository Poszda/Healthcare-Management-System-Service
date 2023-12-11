package com.hmss.springbootserver.services;

import com.hmss.springbootserver.DTOs.appointments.TodayProgramDTO;
import com.hmss.springbootserver.DTOs.statistics.*;
import com.hmss.springbootserver.entities.Doctor;
import com.hmss.springbootserver.DTOs.procedure.ProcedureCounterDTO;
import com.hmss.springbootserver.enums.AppointmentStatus;
import com.hmss.springbootserver.repositories.*;
import com.hmss.springbootserver.utils.models.projections.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.TextStyle;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

@Service
public class StatisticsService {

    private final AppointmentRepository appointmentRepository;
    private final HospitalRepository hospitalRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final ProcedureRepository procedureRepository;

    @Autowired
    public StatisticsService(AppointmentRepository appointmentRepository,
                             HospitalRepository hospitalRepository,
                             DoctorRepository doctorRepository,
                             PatientRepository patientRepository,
                             ProcedureRepository procedureRepository) {
        this.appointmentRepository = appointmentRepository;
        this.hospitalRepository = hospitalRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.procedureRepository = procedureRepository;
    }

    public Object getTodayProgram(Long doctorId) {
        Optional<Doctor> doctorOptional = this.doctorRepository.findById(doctorId);
        if(doctorOptional.isEmpty()) return null;

        Doctor doctor = doctorOptional.get();
        int totalMinutes = (int) Duration.between(doctor.getProgramStart(),doctor.getProgramEnd()).toMinutes();
        int sum = this.appointmentRepository.getSumOfTodayProcedureDuration(doctorId);
        TodayProgramDTO program = new TodayProgramDTO();
        program.setBusy(Math.round((float)(sum)/totalMinutes * 100));
        program.setFree(100 - program.getBusy());
        return program;
    }

    public List<HospitalMonthStatisticDTO> getHospitalPeriodicStats(Long hospitalId) {
        LocalDate startDate = LocalDate.now().minusMonths(5).withDayOfMonth(1);
        LocalDate endDate = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
        System.out.println(endDate);
        List<AppointmentStatisticProjection> list = this.hospitalRepository.findAppointmentsLast6Months(hospitalId,startDate,endDate);
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
        int year = LocalDate.now().getYear();
        return this.hospitalRepository.findPatientVisitBySpeciality(hospitalId,month,year);
    }

    public HospitalOverviewDTO getHospitalOverview(Long hospitalId) {
        int month = LocalDate.now().getMonthValue();
        int year = LocalDate.now().getYear();
        Long appointments = this.hospitalRepository.findAppointmentsNumberByMonth(hospitalId,month,year);
        Double earnings = this.hospitalRepository.findEstimatedEarningsByMonth(hospitalId,month,year);
        List<LocalDate> birthDates = this.hospitalRepository.findPatientsAges(hospitalId,month,year);

        int sum = 0;
        for(LocalDate birthDate : birthDates){
            sum += Period.between(birthDate,LocalDate.now()).getYears();
        }

        return new HospitalOverviewDTO(
                appointments != null?appointments:0,
                earnings != null ?earnings :0,
                birthDates.size() != 0? (float)(sum)/birthDates.size() : 0
        );
    }

    public List<DoctorPatientsVisitsByAgeGroupDTO> getDoctorPatientsVisitsByAgeGroup(Long doctorId) {
        LocalDate startDate = LocalDate.now().minusMonths(5).withDayOfMonth(1);
        LocalDate endDate  = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
        List<DoctorPatientsVisitsProjection> youngsters = this.patientRepository.countDoctorPatientsVisitsLast6Months(doctorId,startDate,endDate,0,25);
        List<DoctorPatientsVisitsProjection> adults = this.patientRepository.countDoctorPatientsVisitsLast6Months(doctorId,startDate,endDate,25,65);
        List<DoctorPatientsVisitsProjection> elders = this.patientRepository.countDoctorPatientsVisitsLast6Months(doctorId,startDate,endDate,65,150);

        //don t need to bother with years since we can have only appointments
        //from last year to the current year on this 6 months statistic
        List<DoctorPatientsVisitsByAgeGroupDTO> list = new ArrayList<>();
        for(int i=0;i<6;i++){
            int month = startDate.getMonth().getValue();
            String monthName = startDate.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault());
            Optional<DoctorPatientsVisitsProjection> optionalStatsYoung = youngsters.stream().filter(el -> el.getMonth().equals(month)).findFirst();
            Optional<DoctorPatientsVisitsProjection> optionalStatsAdults = adults.stream().filter(el -> el.getMonth().equals(month)).findFirst();
            Optional<DoctorPatientsVisitsProjection> optionalStatsElders = elders.stream().filter(el -> el.getMonth().equals(month)).findFirst();

            DoctorPatientsVisitsByAgeGroupDTO statistic = new DoctorPatientsVisitsByAgeGroupDTO(monthName,0,0,0);
            if(optionalStatsYoung.isPresent()){
                statistic.setYoungPatients(optionalStatsYoung.get().getPatients());
            }
            if(optionalStatsAdults.isPresent()){
                statistic.setAdultPatients(optionalStatsAdults.get().getPatients());
            }
            if(optionalStatsElders.isPresent()){
                statistic.setOldPatients(optionalStatsElders.get().getPatients());
            }
            list.add(statistic);
            startDate = startDate.plusMonths(1);
        }
        return list;
    }


    public List<DoctorAppointmentsCounterByStatusDTO> getDoctorAppointmentsCountsByStatus(Long doctorId) {
        List<DoctorAppointmentsCounterByStatusProjection> results =  this.appointmentRepository.countDoctorAppointmentsByStatus(doctorId);
        List<DoctorAppointmentsCounterByStatusDTO> list = Arrays.asList(
                new DoctorAppointmentsCounterByStatusDTO(
                        AppointmentStatus.UPCOMING.getStatus(),
                        0
                ),
                new DoctorAppointmentsCounterByStatusDTO(
                        AppointmentStatus.IN_PROGRESS.getStatus(),
                        0
                ),
                new DoctorAppointmentsCounterByStatusDTO(
                        AppointmentStatus.REVIEWED.getStatus(),
                        0
                )
        );

        for(DoctorAppointmentsCounterByStatusDTO stat : list){
            Optional<DoctorAppointmentsCounterByStatusProjection> itemOptional =  results.stream().filter(el -> el.getStatus().equals(stat.getStatus())).findFirst();
            if(itemOptional.isPresent()){
                stat.setCounter(itemOptional.get().getCounter());
            }
        }
        return list;
    }

    public List<DoctorInterventionsCountByProcedureDTO> getDoctorInterventionsByCountProcedure(Long doctorId) {
        LocalDate startDate = LocalDate.now().withDayOfMonth(1);
        LocalDate endDate = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
        List<DoctorInterventionsCountByProcedureDTO> list = new ArrayList<>();
        for(int i=0;i<12;i++){
            List<ProcedureCounterDTO> procedureCounters =
                    this.procedureRepository.countDoctorInterventionsByProcedure(doctorId,startDate,endDate)
                    .stream().map(el -> new ProcedureCounterDTO(el.getId(),el.getName(),el.getTotal())).toList();

            DoctorInterventionsCountByProcedureDTO d = new DoctorInterventionsCountByProcedureDTO();
            d.setMonth(startDate.getMonth().getDisplayName(TextStyle.FULL,Locale.getDefault()));
            d.setProcedures(procedureCounters);
            list.add(d);
            startDate = startDate.minusMonths(1);
            endDate = endDate.minusMonths(1);
        }
        Collections.reverse(list);
        return list;
    }
}
