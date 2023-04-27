package com.hmss.springbootserver.mappers;

import com.hmss.springbootserver.DTOs.user.UserWithAdminDTO;
import com.hmss.springbootserver.DTOs.user.UserWithDoctorDTO;
import com.hmss.springbootserver.DTOs.user.UserWithPatientDTO;
import com.hmss.springbootserver.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Named("UserMapper")
@Mapper(uses = {PatientMapper.class,DoctorMapper.class, AdminMapper.class})
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Named("UserWithPatientDTO")
    @Mapping(target = "patient" ,qualifiedByName = "patientToPatientDTO")
    UserWithPatientDTO UserWithPatientDTO(User user);

    @Named("UserWithDoctorDTO")
    @Mapping(target = "doctor" ,qualifiedByName = "doctorToDoctorDTO")
    UserWithDoctorDTO UserWithDoctorDTO(User user);

    @Named("UserWithAdminDTO")
    @Mapping(target = "admin" ,qualifiedByName = "adminToAdminDTO")
    UserWithAdminDTO UserWithAdminDTO(User user);

}
