package com.hmss.springbootserver.services;

import com.hmss.springbootserver.DTOs.appointments.AvailableHours;
import com.hmss.springbootserver.DTOs.appointments.DoctorAvailableHours;
import com.hmss.springbootserver.DTOs.hospital.HospitalWithDoctorsDTO;
import com.hmss.springbootserver.entities.Appointment;
import com.hmss.springbootserver.mappers.HospitalMapper;
import com.hmss.springbootserver.repositories.AppointmentRepository;
import com.hmss.springbootserver.repositories.DoctorRepository;
import com.hmss.springbootserver.repositories.HospitalRepository;
import com.hmss.springbootserver.repositories.ProcedureRepository;
import com.hmss.springbootserver.utils.models.AppointmentSimplified;
import com.hmss.springbootserver.utils.models.DoctorProgramSimplified;
import com.hmss.springbootserver.utils.models.FreeTimeInterval;
import com.hmss.springbootserver.utils.models.projections.DoctorProgramProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class AppointmentService {
    private final HospitalRepository hospitalRepository;
    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final ProcedureRepository procedureRepository;

    @Autowired
    public AppointmentService(HospitalRepository hospitalRepository, AppointmentRepository appointmentRepository, DoctorRepository doctorRepository, ProcedureRepository procedureRepository) {
        this.hospitalRepository = hospitalRepository;
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.procedureRepository = procedureRepository;
    }

    public List<HospitalWithDoctorsDTO> getHospitalsAndDoctorsRecommendations(List<String> counties, long speciality){
        var hospitals = this.hospitalRepository.findPossibleHospitalsAndDoctorsForAppointments(counties,speciality);
        return HospitalMapper.INSTANCE.toHospitalWithDoctorsDTOList(hospitals);
    }

    public Object getAvailableAppointments(List<Long>doctorIds,Long procedureId, LocalDateTime startDate, LocalDateTime endDate){
        var appointmentsList = this.appointmentRepository.findAppointmentsByDoctorsInAPeriod(doctorIds,startDate,endDate);
        var doctorsList  = this.doctorRepository.findDoctorsPrograms(doctorIds);
        var procedureDuration = this.procedureRepository.findDurationById(procedureId);

        HashMap<Long,List<AppointmentSimplified>> appointmentsMap = mapSimplifiedAppointments(appointmentsList);
        HashMap<Long, DoctorProgramSimplified> programMap = mapDoctorsProgram(doctorsList);
        Long numberOfDays = DAYS.between(startDate.toLocalDate(),endDate.toLocalDate()) + 1;

        List<DoctorAvailableHours> doctorsAvailableHours = new ArrayList<>();
        for (Long id: doctorIds) {
            //System.out.println("NEXT DOCTOR: \n");
            List<AvailableHours> availableHours = new ArrayList<>();
            LocalDate day = startDate.toLocalDate();
            for (int i = 0; i < numberOfDays; i++) {
                var dayCopy = day;
                List<AppointmentSimplified> dayAppointments = appointmentsMap.get(id) == null
                        ?new ArrayList<>()
                        :appointmentsMap.get(id)
                        .stream()
                        .filter(el -> dayCopy.equals(el.getDate()))
                        .collect(Collectors.toList());

                List<FreeTimeInterval> freeTimeIntervals = this.calculateFreeTimeIntervalsOfADay(
                        dayAppointments,
                        programMap.get(id).getProgramStart(),
                        programMap.get(id).getProgramEnd()
                );

                List<LocalTime> hours = calculateAvailableHoursOfADay(freeTimeIntervals,procedureDuration);

                //System.out.println(freeTimeIntervals);
                //System.out.println(day + " : "+hours);
                //System.out.println();
                availableHours.add(new AvailableHours(dayCopy,hours));
                day = day.plusDays(1);
            }
            doctorsAvailableHours.add(new DoctorAvailableHours(id,availableHours));
        }
        return doctorsAvailableHours;
    }
    public List<LocalTime> calculateAvailableHoursOfADay(List<FreeTimeInterval> freeTimeIntervals,int procedureDuration){
        int dif = 15; //slice intervals by 15 minutes
        List<LocalTime> list = new ArrayList<>();
        for (FreeTimeInterval el: freeTimeIntervals) {
               var x = el.getIntervalStart();
               while(x <= el.getIntervalEnd() - procedureDuration){
                   LocalTime time = LocalTime.of(0,0).plusMinutes(x);
                   list.add(time);
                   x +=dif;
               }
        }
        return list;
    }

    public List<FreeTimeInterval> calculateFreeTimeIntervalsOfADay(List<AppointmentSimplified> appointments,int start, int end){
        List<FreeTimeInterval> freeTimeIntervals = new ArrayList<>();
        //System.out.println(appointments);
        if(appointments.isEmpty()){
            freeTimeIntervals.add(new FreeTimeInterval(start, end));
        }
        else{
            int pivot = start;
            for (AppointmentSimplified appointment: appointments) {
                if(pivot != appointment.getStartMinute())
                {
                    freeTimeIntervals.add(new FreeTimeInterval(pivot, appointment.getStartMinute()));
                }
                pivot = appointment.getEndMinute();
            }
            if(pivot!=end){ // add the left time
                freeTimeIntervals.add(new FreeTimeInterval(pivot,end));
            }
        }
        return freeTimeIntervals;
    }

    public HashMap<Long,List<AppointmentSimplified>> mapSimplifiedAppointments(List<Appointment> list){
        HashMap<Long,List<AppointmentSimplified>> map = new HashMap<>();
        for (var el: list) {
            AppointmentSimplified a = new AppointmentSimplified(
                    el.getDate().toLocalTime().toSecondOfDay()/60,
                    el.getDate().toLocalTime().toSecondOfDay()/60 + el.getProcedure().getDuration(),
                    el.getId(),
                    el.getDoctor().getId(),
                    el.getDate().toLocalDate()
            );
            if(map.containsKey(a.getDoctorId())){
                map.get(a.getDoctorId()).add(a);
            }
            else{
                List<AppointmentSimplified> newList = new ArrayList<>();
                newList.add(a);
                map.put(a.getDoctorId(),newList);
            }
        }

        return map;
    }

    public HashMap<Long, DoctorProgramSimplified> mapDoctorsProgram(List<DoctorProgramProjection> list){
        HashMap<Long, DoctorProgramSimplified> map = new HashMap<>();
        for(DoctorProgramProjection el:list) {
            DoctorProgramSimplified d = new DoctorProgramSimplified(
                    el.getProgramStart().toSecondOfDay()/60,
                    el.getProgramEnd().toSecondOfDay()/60
            );
            map.put(el.getId(),d);
        }
        return map;
    }


}
