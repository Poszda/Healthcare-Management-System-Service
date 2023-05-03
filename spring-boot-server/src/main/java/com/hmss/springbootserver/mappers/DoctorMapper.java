package com.hmss.springbootserver.mappers;

import com.hmss.springbootserver.DTOs.doctor.DoctorDTO;
import com.hmss.springbootserver.DTOs.doctor.DoctorWithUserDTO;
import com.hmss.springbootserver.entities.Doctor;
import com.hmss.springbootserver.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Named("DoctorMapper")
@Mapper(uses = {UserMapper.class})
public interface DoctorMapper {
    DoctorMapper INSTANCE = Mappers.getMapper(DoctorMapper.class);

    @Named("toDoctorDTO")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "hospital.id", target = "hospitalId")
    DoctorDTO toDoctorDTO(Doctor doctor);

    @Named("toDoctorWithUserDTO")
    @Mapping(source = "hospital.id", target = "hospitalId")
    @Mapping(target = "user" ,qualifiedByName = "toUserDTO")
    DoctorWithUserDTO toDoctorWithUserDTO(Doctor doctor);
}
