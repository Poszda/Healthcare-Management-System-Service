package com.hmss.springbootserver.mappers;


import com.hmss.springbootserver.DTOs.speciality.SpecialityDTO;
import com.hmss.springbootserver.DTOs.speciality.SpecialityWithProceduresDTO;
import com.hmss.springbootserver.entities.Speciality;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Named("SpecialityMapper")
@Mapper(uses = ProcedureMapper.class)
public interface SpecialityMapper {

    SpecialityMapper INSTANCE = Mappers.getMapper(SpecialityMapper.class);

    @Named("toSpecialityDTO")
    SpecialityDTO toSpecialityDTO(Speciality speciality);

    @Named("toSpecialityDTOList")
    @IterableMapping(qualifiedByName = "toSpecialityDTO")
    List<SpecialityDTO> toSpecialityDTOList(List<Speciality> specialities);

    @Named("toSpecialityWithProceduresDTO")
    @Mapping(source = "procedures", target = "procedures",qualifiedByName="toProcedureDTO")
    SpecialityWithProceduresDTO toSpecialityWithProceduresDTO(Speciality speciality);

    @Named("toSpecialityWithProceduresDTOList")
    @IterableMapping(qualifiedByName = "toSpecialityWithProceduresDTO")
    List<SpecialityWithProceduresDTO> toSpecialityWithProceduresDTOList(List<Speciality> specialities);
}
