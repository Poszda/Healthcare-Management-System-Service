package com.hmss.springbootserver.mappers;

import com.hmss.springbootserver.DTOs.hospital.HospitalWithDoctorsDTO;
import com.hmss.springbootserver.DTOs.speciality.SpecialityWithProceduresDTO;
import com.hmss.springbootserver.DTOs.user.UserDTO;
import com.hmss.springbootserver.entities.Hospital;
import com.hmss.springbootserver.entities.Speciality;
import com.hmss.springbootserver.entities.User;
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

    @Named("toSpecialityWithProceduresDTO")
    @Mapping(source = "procedures", target = "procedures",qualifiedByName="toProcedureDTO")
    SpecialityWithProceduresDTO toSpecialityWithProceduresDTO(Speciality speciality);

    @Named("toSpecialityWithProceduresDTOList")
    @IterableMapping(qualifiedByName = "toSpecialityWithProceduresDTO")
    List<SpecialityWithProceduresDTO> toSpecialityWithProceduresDTOList(List<Speciality> specialities);
}
