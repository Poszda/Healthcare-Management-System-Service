package com.hmss.springbootserver.mappers;

import com.hmss.springbootserver.DTOs.doctor.DoctorDTO;
import com.hmss.springbootserver.entities.Doctor;
import com.hmss.springbootserver.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Named("DoctorMapper")
@Mapper
public interface DoctorMapper {
    DoctorMapper INSTANCE = Mappers.getMapper(DoctorMapper.class);

    @Named("doctorToDoctorDTO")
    @Mapping(source = "user", target = "userId", qualifiedByName = "mapUserId")
    DoctorDTO doctorToDoctorDTO(Doctor doctor);

    // Map userId from User entity
    @Named("mapUserId")
    default Long mapUserId(User user) {
        return user != null ? user.getId() : null;
    }
}
