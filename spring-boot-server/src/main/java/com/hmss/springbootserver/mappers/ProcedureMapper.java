package com.hmss.springbootserver.mappers;

import com.hmss.springbootserver.DTOs.procedure.ProcedureDTO;
import com.hmss.springbootserver.DTOs.speciality.SpecialityWithProceduresDTO;
import com.hmss.springbootserver.entities.Procedure;
import com.hmss.springbootserver.entities.Speciality;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Named("ProcedureMapper")
@Mapper
public interface ProcedureMapper {
    ProcedureMapper INSTANCE = Mappers.getMapper(ProcedureMapper.class);

    @Named("toProcedureDTO")
    @Mapping(source = "speciality.id", target = "specialityId")
    ProcedureDTO toProcedureDTO(Procedure procedure);
}
