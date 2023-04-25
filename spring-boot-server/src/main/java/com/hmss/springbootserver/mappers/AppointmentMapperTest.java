package com.hmss.springbootserver.mappers;

import com.hmss.springbootserver.DTOs.AppointmentDTOTest;
import com.hmss.springbootserver.entities.Appointment;

public class AppointmentMapperTest {
    public static AppointmentDTOTest appointmentToDto(Appointment appointment) {
        AppointmentDTOTest appointmentDTO = new AppointmentDTOTest();
        appointmentDTO.setId(appointment.getId());
        appointmentDTO.setPatient(PatientMapperTest.patientToDtoNoAppointments(appointment.getPatient()));
        return appointmentDTO;
    }

    public static AppointmentDTOTest appointmentToDtoRestricted(Appointment appointment) {
        AppointmentDTOTest appointmentDTO = new AppointmentDTOTest();
        appointmentDTO.setId(appointment.getId());
        appointmentDTO.setPatient(null);
        return appointmentDTO;
    }

    public static AppointmentDTOTest appointmentToDtoNoPatient(Appointment appointment) {
        AppointmentDTOTest appointmentDTO = new AppointmentDTOTest();
        appointmentDTO.setId(appointment.getId());
        appointmentDTO.setPatient(null);
        return appointmentDTO;
    }


}
