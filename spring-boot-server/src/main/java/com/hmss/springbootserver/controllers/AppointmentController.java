package com.hmss.springbootserver.controllers;

import com.hmss.springbootserver.DTOs.appointments.*;
import com.hmss.springbootserver.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/getAppointmentOptionals/{counties}/{procedureId}")
    @CrossOrigin(origins = "*")
    public AppointmentOptionalsDTO getAppointmentOptionals(@PathVariable("counties") List<String> counties, @PathVariable("procedureId") long procedureId){
        return this.appointmentService.getAppointmentOptionals(counties,procedureId);
    }
    @GetMapping("/getAvailableAppointments/{ids}/{procedureId}/{startDate}/{endDate}")
    @CrossOrigin(origins = "*")
    public Object getAvailableAppointments(@PathVariable("ids") List<Long> ids,
                                           @PathVariable("procedureId") long procedureId,
                                           @PathVariable("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
                                           @PathVariable("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        System.out.println(startDate);
        System.out.println(endDate);
        return this.appointmentService.getAvailableAppointments(ids,procedureId,startDate,endDate);

    }

    @PostMapping("/createAppointment")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Object> createAppointment(@RequestBody CreateAppointmentRequestDTO appointment){

        System.out.println(appointment.toString());
        return this.appointmentService.createAppointment(appointment);
    }

    @DeleteMapping("/deleteAppointment/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> deleteAppointment(@PathVariable("id") Long id) {
        return this.appointmentService.deleteAppointment(id);
    }

    @GetMapping("/getAppointmentsCards/{patientId}")
    @CrossOrigin(origins = "*")
    public List<AppointmentCardDTO> getAppointmentsCards(@PathVariable("patientId") Long patientId){
        return this.appointmentService.getAppointmentsCards(patientId);
    }

    @GetMapping("/getDoctorAppointments/{doctorId}")
    @CrossOrigin(origins = "*")
    public List<DoctorAppointmentDTO> getDoctorAppointments(@PathVariable("doctorId") Long doctorId){
        return this.appointmentService.getDoctorAppointments(doctorId);
    }

    @PostMapping("/createDiagnostic")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Object> createDiagnostic(@RequestBody CreateDiagnosticRequestDTO appointment){
        return this.appointmentService.createDiagnostic(appointment);
    }

    @GetMapping("/getPatientDiagnostics/{patientId}")
    @CrossOrigin(origins = "*")
    public List<PatientDiagnosticExtendedDTO> getPatientDiagnostics(@PathVariable("patientId") Long patientId){
        return this.appointmentService.getPatientDiagnostics(patientId);
    }

    @GetMapping("/getDoctorTodayNextAppointments/{doctorId}")
    @CrossOrigin(origins = "*")
    public List<AppointmentNextDTO> getDoctorTodayNextAppointments(@PathVariable("doctorId") Long doctorId){
        return this.appointmentService.getDoctorTodayNextAppointments(doctorId);
    }
    @GetMapping("/getPatientNextAppointments/{patientId}")
    @CrossOrigin(origins = "*")
    public List<AppointmentNextDTO> getPatientNextAppointments(@PathVariable("patientId") Long patientId){
        return this.appointmentService.getPatientNextAppointments(patientId);
    }


}
