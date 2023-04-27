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
@Mapper(uses = {PatientMapper.class,DoctorMapper.class, AdminMapper.class})
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Named("UserToPatientLoginDTO")
    @Mapping(target = "patient" ,qualifiedByName = "patientToPatientDTO")
    PatientLoginDTO UserToPatientLoginDTO(User user);

    @Named("UserToDoctorLoginDTO")
    @Mapping(target = "doctor" ,qualifiedByName = "doctorToDoctorDTO")
    DoctorLoginDTO UserToDoctorLoginDTO(User user);

    @Named("UserToAdminLoginDTO")
    @Mapping(target = "admin" ,qualifiedByName = "adminToAdminDTO")
    AdminLoginDTO UserToAdminLoginDTO(User user);

}
