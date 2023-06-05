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

    @Named("toDoctorWithUserAndHospitalAndSpecialityDTO")
    DoctorWithUserAndHospitalAndSpecialityDTO toDoctorWithUserAndHospitalAndSpecialityDTO(Doctor doctor);

    @Named("toMedicationEntity")
    Medication toMedicationEntity(MedicationDTO medicationDTO);

    @Named("toMedicationEntityList")
    @IterableMapping(qualifiedByName = "toMedicationEntity")
    List<Medication> toMedicationEntityList(List<MedicationDTO> medicationDTOs);
}
