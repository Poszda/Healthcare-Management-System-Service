package com.hmss.springbootserver.mappers;


import com.hmss.springbootserver.DTOs.appointments.AppointmentCardDTO;
import com.hmss.springbootserver.DTOs.appointments.AppointmentNextDTO;
import com.hmss.springbootserver.DTOs.appointments.MedicationDTO;
import com.hmss.springbootserver.entities.Medication;
import com.hmss.springbootserver.utils.models.projections.AppointmentCardProjection;
import com.hmss.springbootserver.utils.models.projections.AppointmentNextProjection;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Named("AppointmentMapper")
@Mapper
public interface AppointmentMapper {
    AppointmentMapper INSTANCE = Mappers.getMapper(AppointmentMapper.class);

    @Named("toMedicationEntity")
    Medication toMedicationEntity(MedicationDTO medicationDTO);

    @Named("toAppointmentNextDTO")
    AppointmentNextDTO toAppointmentNextDTO(AppointmentNextProjection appointment);

    @Named("toAppointmentCardDTO")
    AppointmentCardDTO toAppointmentCardDTO(AppointmentCardProjection appointment);

    @Named("toMedicationEntityList")
    @IterableMapping(qualifiedByName = "toMedicationEntity")
    List<Medication> toMedicationEntityList(List<MedicationDTO> medicationDTOs);

    @Named("toAppointmentNextDTOList")
    @IterableMapping(qualifiedByName = "toAppointmentNextDTO")
    List<AppointmentNextDTO> toAppointmentNextDTOList(List<AppointmentNextProjection> appointments);

    @Named("toAppointmentCardDTOList")
    @IterableMapping(qualifiedByName = "toAppointmentCardDTO")
    List<AppointmentCardDTO> toAppointmentCardDTOList(List<AppointmentCardProjection> appointments);
}
