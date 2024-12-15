package com.hmss.springbootserver.mappers;

import com.hmss.springbootserver.DTOs.patient.PatientBioDTO;
import com.hmss.springbootserver.DTOs.patient.PatientDTO;
import com.hmss.springbootserver.DTOs.patient.PatientWithUserDTO;
import com.hmss.springbootserver.entities.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Named("PatientMapper")
@Mapper
public interface PatientMapper {
    PatientMapper INSTANCE = Mappers.getMapper(PatientMapper.class);

    @Named("toPatientDTO")
    @Mapping(source = "user.id", target = "userId")
    PatientDTO toPatientDTO(Patient patient);

    @Named("toPatientBioDTO")
    @Mapping(source = "profileImage", target = "profileImage")
    PatientBioDTO toPatientBioDTO(Patient patient, String profileImage);

    @Named("toPatientWithUserDTO")
    PatientWithUserDTO toPatientWithUserDTO(Patient patient);
}
