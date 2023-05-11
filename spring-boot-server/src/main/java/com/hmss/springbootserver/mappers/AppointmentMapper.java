package com.hmss.springbootserver.mappers;

import com.hmss.springbootserver.DTOs.appointments.AppointmentWidgetDTO;
import com.hmss.springbootserver.DTOs.doctor.DoctorDTO;
import com.hmss.springbootserver.DTOs.doctor.DoctorWithUserAndHospitalAndSpecialityDTO;
import com.hmss.springbootserver.DTOs.doctor.DoctorWithUserDTO;
import com.hmss.springbootserver.DTOs.hospital.HospitalDTO;
import com.hmss.springbootserver.DTOs.patient.PatientDTO;
import com.hmss.springbootserver.DTOs.procedure.ProcedureDTO;
import com.hmss.springbootserver.DTOs.speciality.SpecialityDTO;
import com.hmss.springbootserver.DTOs.user.UserDTO;
import com.hmss.springbootserver.entities.*;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

public interface AppointmentMapper {

    @Named("toUserDTO")
    @Mapping(source = "doctor.id", target = "doctorId")
    @Mapping(source = "admin.id", target = "adminId")
    @Mapping(source = "patient.id", target = "patientId")
    UserDTO toUserDTO(User user);

    @Named("toPatientDTO")
    @Mapping(source = "user.id", target = "userId")
    PatientDTO toPatientDTO(Patient patient);

    @Named("toDoctorDTO")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "hospital.id", target = "hospitalId")
    DoctorDTO toDoctorDTO(Doctor doctor);

    @Named("toSpecialityDTO")
    SpecialityDTO toSpecialityDTO(Speciality speciality);

    @Named("toHospitalDTO")
    HospitalDTO toHospitalDTO(Hospital hospital);

    @Named("toProcedureDTO")
    @Mapping(source = "speciality.id", target = "specialityId")
    ProcedureDTO toProcedureDTO(Procedure procedure);

    @Named("toDoctorWithUserAndHospitalAndSpecialityDTO")
    @Mapping(target = "hospital", qualifiedByName = "toHospitalDTO")
    @Mapping(target = "user" ,qualifiedByName = "toUserDTO")
    @Mapping(target = "speciality" ,qualifiedByName = "toSpecialityDTO")
    DoctorWithUserAndHospitalAndSpecialityDTO toDoctorWithUserAndHospitalAndSpecialityDTO(Doctor doctor);

    @Named("toAppointmentWidgetDTO")
    @Mapping(target = "doctor", qualifiedByName = "toDoctorWithUserAndHospitalAndSpecialityDTO")
    @Mapping(target = "procedure", qualifiedByName = "toProcedureDTO")
    AppointmentWidgetDTO toAppointmentWidgetDTO(Appointment appointment);


}
