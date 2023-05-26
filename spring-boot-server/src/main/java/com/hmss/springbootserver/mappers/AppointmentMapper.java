package com.hmss.springbootserver.mappers;

import com.hmss.springbootserver.DTOs.appointments.AppointmentWidgetDTO;
import com.hmss.springbootserver.DTOs.appointments.MedicationDTO;
import com.hmss.springbootserver.DTOs.doctor.DoctorDTO;
import com.hmss.springbootserver.DTOs.doctor.DoctorWithUserAndHospitalAndSpecialityDTO;
import com.hmss.springbootserver.DTOs.hospital.HospitalDTO;
import com.hmss.springbootserver.DTOs.patient.PatientDTO;
import com.hmss.springbootserver.DTOs.procedure.ProcedureDTO;
import com.hmss.springbootserver.DTOs.speciality.SpecialityDTO;
import com.hmss.springbootserver.DTOs.user.UserDTO;
import com.hmss.springbootserver.entities.*;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Named("AppointmentMapper")
@Mapper()
public interface AppointmentMapper {
    AppointmentMapper INSTANCE = Mappers.getMapper(AppointmentMapper.class);

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

    @Named("toMedicationEntity")
    Medication toMedicationEntity(MedicationDTO medicationDTO);

    @Named("toMedicationEntityList")
    @IterableMapping(qualifiedByName = "toMedicationEntity")
    List<Medication> toMedicationEntityList(List<MedicationDTO> medicationDTOs);




}
