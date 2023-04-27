package com.hmss.springbootserver.controllers;

import com.hmss.springbootserver.DTOs.AppointmentDTOTest;
import com.hmss.springbootserver.DTOs.PatientDTOTest;
import com.hmss.springbootserver.DTOs.UserDTOTest;
import com.hmss.springbootserver.entities.*;
import com.hmss.springbootserver.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/test")
public class TestController {

    private final TestService authService;
    @Autowired
    public TestController(TestService authService) {
        this.authService = authService;
    }

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return this.authService.getAllUsers();
    }
    @GetMapping("/patients")
    public List<Patient> getAllPatients(){
        return this.authService.getAllPatients();
    }
    @GetMapping("/appointments")
    public List<Appointment> getAllAppointments(){
        return this.authService.getAllAppointments();
    }

    @GetMapping("/createUserWithPatient")
    public boolean createUserWithPatient(){
        return this.authService.createUserWithPatient();
    }

    @GetMapping("/createUser")
    public boolean createUser(){
        return this.authService.createUser();
    }
    @GetMapping("/getFirstUser")
    public UserDTOTest getFirstUser() {
        return this.authService.getFirstUser();
    }

    @GetMapping("/getFirstPatient")
    public Object getFirstPatient() {
        return this.authService.getFirstPatient();
    }

    @GetMapping("/getFirstUserRestricted")
    public UserDTOTest getFirstUserRestricted() {
        return this.authService.getFirstUserRestricted();
    }

    @GetMapping("/getFirstPatientRestricted")
    public PatientDTOTest getFirstPatientRestricted() {
        return this.authService.getFirstPatientRestricted();
    }
    @GetMapping("/getCustomUser")
    public UserDTOTest getCustomUser(){
        return this.authService.getCustomUser();
    }
    @GetMapping("/getFirstAppointment")
    public AppointmentDTOTest getFirstAppointment(){
        return this.authService.getFirstAppointment();
    }
    @GetMapping("/getFirstAppointmentRestricted")
    public AppointmentDTOTest getFirstAppointmentRestricted(){
        return this.authService.getFirstAppointmentRestricted();
    }

    @GetMapping("/hospitals")
    public Hospital getHospitals(){
        this.authService.getHospitals();
        return null;
    }
    @GetMapping("/procedure")
    public Procedure getProcedures(){
        this.authService.getProcedures();
        return null;
    }

    @GetMapping("/specialities")
    public Speciality getSpecialities(){
        return this.authService.getSpecialities();
    }
    @GetMapping("/diagnostics")
    public Diagnostic getDiagnostics(){
        return this.authService.getDiagnostics();
    }
    @GetMapping("/medications")
    public Medication getMedications(){
        return this.authService.getMedications();
    }
    @GetMapping("/admins")
    public Admin getAdmins(){
        return this.authService.getAdmins();
    }
    @GetMapping("/doctors")
    public Doctor getDoctors(){
        return this.authService.getDoctors();
    }

    @GetMapping("/addProcedureToHospital")
    public boolean addProcedureToHospital(){
        return this.authService.addProcedureToHospital();
    }

    @GetMapping("/addHospitalToProcedure")
    public boolean addHospitalToProcedure(){
        return this.authService.addHospitalToProcedure();
    }
    @GetMapping("/removeProcedureFromHospital")
    public boolean removeProcedureFromHospital(){
        return this.authService.removeProcedureFromHospital();
    }

    @GetMapping("/customQuery")
    public boolean customQuery(){
        this.authService.customQuery();
        return true;
    }
    @GetMapping("/cascadeTypeTestPersist")
    public boolean cascadeTypeTestPersist() {
        //this.authService.cascadeTypeTestPersist();
        return true;
    }
    @GetMapping("/cascadeTypeTestRemove")
    public boolean cascadeTypeTestRemove() {
        //this.authService.cascadeTypeTestRemove();
        return true;
    }
    @GetMapping("/conversion1")
    public void conversion1(){
        this.authService.conversion1();
    }
}
