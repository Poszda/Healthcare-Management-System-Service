package com.hmss.springbootserver.mappers;

import com.hmss.springbootserver.DTOs.patient.PatientSimpleDTO;
import com.hmss.springbootserver.entities.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Named("PatientMapper2")
@Mapper
public interface PatientMapper2 {
    PatientMapper2 INSTANCE = Mappers.getMapper(PatientMapper2.class);

    @Named("patientToPatientSimpleDTO")
    PatientSimpleDTO patientToPatientSimpleDTO(Patient patient);
}
