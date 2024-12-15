package com.hmss.springbootserver.mappers;

import com.hmss.springbootserver.DTOs.user.AdminLoginDTO;
import com.hmss.springbootserver.DTOs.user.DoctorLoginDTO;
import com.hmss.springbootserver.DTOs.user.PatientLoginDTO;
import com.hmss.springbootserver.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Named("UserMapper")
@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Named("UserToPatientLoginDTO")
    @Mapping(source = "patient.user.id", target = "patient.userId")
    PatientLoginDTO UserToPatientLoginDTO(User user);

    @Named("UserToDoctorLoginDTO")
    @Mapping(source = "doctor.user.id", target = "doctor.userId")
    @Mapping(source = "doctor.hospital.id", target = "doctor.hospitalId")
    DoctorLoginDTO UserToDoctorLoginDTO(User user);

    @Named("UserToAdminLoginDTO")
    @Mapping(source = "admin.user.id", target = "admin.userId")
    AdminLoginDTO UserToAdminLoginDTO(User user);

}
