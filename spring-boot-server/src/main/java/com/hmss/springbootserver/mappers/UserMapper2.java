package com.hmss.springbootserver.mappers;

import com.hmss.springbootserver.DTOs.user.UserFullDTO;
import com.hmss.springbootserver.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Named("UserMapper2")
@Mapper(uses = PatientMapper2.class)
public interface UserMapper2 {
    UserMapper2 INSTANCE = Mappers.getMapper(UserMapper2.class);

    @Named("userToFullDTO")
    @Mapping(target = "patient" ,qualifiedByName = "patientToPatientSimpleDTO")
    UserFullDTO userToFullDTO(User user);

}
