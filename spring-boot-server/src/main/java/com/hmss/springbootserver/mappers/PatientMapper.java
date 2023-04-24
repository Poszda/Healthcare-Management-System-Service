package com.hmss.springbootserver.mappers;

import com.hmss.springbootserver.DTOs.AppointmentDTO;
import com.hmss.springbootserver.DTOs.PatientDTO;
import com.hmss.springbootserver.entities.Appointment;
import com.hmss.springbootserver.entities.Patient;

import java.util.ArrayList;


public class PatientMapper {

    //Full DTO
    public static PatientDTO patientToDto(Patient patient){
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setId(patient.getId());
        patientDTO.setPhone(patient.getPhone());
        patientDTO.setUser(UserMapper.userToDtoNoPatient(patient.getUser()));
        var listDTO = new ArrayList<AppointmentDTO>();
        var list = patient.getAppointments();
        for (Appointment el : list) {
            listDTO.add(AppointmentMapper.appointmentToDtoNoPatient(el));
        }
        patientDTO.setAppointments(listDTO);
        return patientDTO;
    }

    // Only Table Attributes DTO
    public static PatientDTO patientToDtoRestricted(Patient patient){
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setId(patient.getId());
        patientDTO.setPhone(patient.getPhone());
        return patientDTO;
    }

    //Full DTO Except User
    public static PatientDTO patientToDtoNoUser(Patient patient){
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setId(patient.getId());
        patientDTO.setPhone(patient.getPhone());
        var listDTO = new ArrayList<AppointmentDTO>();
        var list = patient.getAppointments();
        for (Appointment el : list) {
            listDTO.add(AppointmentMapper.appointmentToDtoNoPatient(el));
        }
        patientDTO.setAppointments(listDTO);
        patientDTO.setUser(null); //except user
        return patientDTO;
    }

    //Full DTO Except Appointments
    public static PatientDTO patientToDtoNoAppointments(Patient patient){
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setId(patient.getId());
        patientDTO.setPhone(patient.getPhone());
        patientDTO.setUser(UserMapper.userToDtoNoPatient(patient.getUser()));
        patientDTO.setAppointments(null); //except appointments
        return patientDTO;
    }

    //TO ENTITY
    public static Patient DTOtoPatientEntity(PatientDTO patientDTO){
        Patient patient = new Patient();
        patient.setPhone(patientDTO.getPhone());
        return patient;
    }
}
