package com.hmss.springbootserver.controllers;

import com.hmss.springbootserver.DTOs.appointments.*;
import com.hmss.springbootserver.services.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public ResponseEntity<AppointmentOptionalsDTO> getAppointmentOptionals(@PathVariable("counties") List<String> counties, @PathVariable("procedureId") long procedureId){
        return new ResponseEntity<>(this.appointmentService.getAppointmentOptionals(counties,procedureId), HttpStatus.OK);
    }

    @GetMapping("/getAvailableAppointments")
    public ResponseEntity<List<DoctorAvailableHoursDTO>> getAvailableAppointments(
            @RequestParam List<Long> doctorIds,
            @RequestParam long procedureId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        return new ResponseEntity<>(this.appointmentService.getAvailableAppointments(doctorIds, procedureId, startDate, endDate), HttpStatus.OK);
    }

    @PostMapping("/createAppointment")
    public ResponseEntity<CreateAppointmentRequestDTO> createAppointment(@Valid @RequestBody CreateAppointmentRequestDTO appointment){
        return new ResponseEntity<>(this.appointmentService.createAppointment(appointment), HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteAppointment/{id}")
    public ResponseEntity<Object> deleteAppointment(@PathVariable("id") Long id) {
        this.appointmentService.deleteAppointment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/getAppointmentsCards/{patientId}")
    public ResponseEntity<List<AppointmentCardDTO>> getAppointmentsCards(@PathVariable("patientId") Long patientId){
        return new ResponseEntity<>(this.appointmentService.getAppointmentsCards(patientId), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('DOCTOR')")
    @GetMapping("/getDoctorAppointments/{doctorId}")
    public ResponseEntity<List<DoctorAppointmentDTO>> getDoctorAppointments(@PathVariable("doctorId") Long doctorId){
        return new ResponseEntity<>(this.appointmentService.getDoctorAppointments(doctorId), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('DOCTOR')")
    @PostMapping("/createDiagnostic")
    public ResponseEntity<Object> createDiagnostic(@Valid @RequestBody CreateDiagnosticRequestDTO appointment){
        this.appointmentService.createDiagnostic(appointment);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/getPatientDiagnostics/{patientId}")
    public ResponseEntity<List<PatientDiagnosticExtendedDTO>> getPatientDiagnostics(@PathVariable("patientId") Long patientId){
        return new ResponseEntity<>(this.appointmentService.getPatientDiagnostics(patientId), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('DOCTOR')")
    @GetMapping("/getDoctorTodayNextAppointments/{doctorId}")
    public ResponseEntity<List<AppointmentNextDTO>> getDoctorTodayNextAppointments(@PathVariable("doctorId") Long doctorId){
        return new ResponseEntity<>(this.appointmentService.getDoctorTodayNextAppointments(doctorId), HttpStatus.OK);
    }

    @GetMapping("/getPatientNextAppointments/{patientId}")
    public ResponseEntity<List<AppointmentNextDTO>> getPatientNextAppointments(@PathVariable("patientId") Long patientId){
        return new ResponseEntity<>(this.appointmentService.getPatientNextAppointments(patientId), HttpStatus.OK);
    }


}
