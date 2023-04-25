package com.hmss.springbootserver.mappers;

import com.hmss.springbootserver.DTOs.user.UserFullDTO;
import com.hmss.springbootserver.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Named("UserMapper")
@Mapper(uses = PatientMapper.class)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Named("userToFullDTO")
    @Mapping(target = "patient" ,qualifiedByName = "patientToPatientSimpleDTO")
    UserFullDTO userToFullDTO(User user);

}
