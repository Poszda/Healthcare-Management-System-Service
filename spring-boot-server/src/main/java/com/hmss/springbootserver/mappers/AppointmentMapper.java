package com.hmss.springbootserver.mappers;

import com.hmss.springbootserver.DTOs.AppointmentDTO;
import com.hmss.springbootserver.entities.Appointment;

public class AppointmentMapper {
    public static AppointmentDTO appointmentToDto(Appointment appointment) {
        AppointmentDTO appointmentDTO = new AppointmentDTO();
        appointmentDTO.setId(appointment.getId());
        appointmentDTO.setPatient(PatientMapper.patientToDtoNoAppointments(appointment.getPatient()));
        return appointmentDTO;
    }

    public static AppointmentDTO appointmentToDtoRestricted(Appointment appointment) {
        AppointmentDTO appointmentDTO = new AppointmentDTO();
        appointmentDTO.setId(appointment.getId());
        appointmentDTO.setPatient(null);
        return appointmentDTO;
    }

    public static AppointmentDTO appointmentToDtoNoPatient(Appointment appointment) {
        AppointmentDTO appointmentDTO = new AppointmentDTO();
        appointmentDTO.setId(appointment.getId());
        appointmentDTO.setPatient(null);
        return appointmentDTO;
    }


}
