package com.hmss.springbootserver.mappers;

import com.hmss.springbootserver.DTOs.AppointmentDTOTest;
import com.hmss.springbootserver.DTOs.PatientDTOTest;
import com.hmss.springbootserver.entities.Appointment;
import com.hmss.springbootserver.entities.Patient;

import java.util.ArrayList;


public class PatientMapperTest {

    //Full DTO
    public static PatientDTOTest patientToDto(Patient patient){
        PatientDTOTest patientDTO = new PatientDTOTest();
        patientDTO.setId(patient.getId());
        patientDTO.setPhone(patient.getPhone());
        patientDTO.setUser(UserMapperTest.userToDtoNoPatient(patient.getUser()));
        var listDTO = new ArrayList<AppointmentDTOTest>();
        var list = patient.getAppointments();
        for (Appointment el : list) {
            listDTO.add(AppointmentMapperTest.appointmentToDtoNoPatient(el));
        }
        patientDTO.setAppointments(listDTO);
        return patientDTO;
    }

    // Only Table Attributes DTO
    public static PatientDTOTest patientToDtoRestricted(Patient patient){
        PatientDTOTest patientDTO = new PatientDTOTest();
        patientDTO.setId(patient.getId());
        patientDTO.setPhone(patient.getPhone());
        return patientDTO;
    }

    //Full DTO Except User
    public static PatientDTOTest patientToDtoNoUser(Patient patient){
        PatientDTOTest patientDTO = new PatientDTOTest();
        patientDTO.setId(patient.getId());
        patientDTO.setPhone(patient.getPhone());
        var listDTO = new ArrayList<AppointmentDTOTest>();
        var list = patient.getAppointments();
        for (Appointment el : list) {
            listDTO.add(AppointmentMapperTest.appointmentToDtoNoPatient(el));
        }
        patientDTO.setAppointments(listDTO);
        patientDTO.setUser(null); //except user
        return patientDTO;
    }

    //Full DTO Except Appointments
    public static PatientDTOTest patientToDtoNoAppointments(Patient patient){
        PatientDTOTest patientDTO = new PatientDTOTest();
        patientDTO.setId(patient.getId());
        patientDTO.setPhone(patient.getPhone());
        patientDTO.setUser(UserMapperTest.userToDtoNoPatient(patient.getUser()));
        patientDTO.setAppointments(null); //except appointments
        return patientDTO;
    }

    //TO ENTITY
    public static Patient DTOtoPatientEntity(PatientDTOTest patientDTO){
        Patient patient = new Patient();
        patient.setPhone(patientDTO.getPhone());
        return patient;
    }
}
