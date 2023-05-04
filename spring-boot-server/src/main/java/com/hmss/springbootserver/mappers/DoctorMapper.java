package com.hmss.springbootserver.mappers;

import com.hmss.springbootserver.DTOs.doctor.DoctorDTO;
import com.hmss.springbootserver.DTOs.doctor.DoctorWithUserDTO;
import com.hmss.springbootserver.DTOs.user.UserDTO;
import com.hmss.springbootserver.entities.Doctor;
import com.hmss.springbootserver.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Named("DoctorMapper")
@Mapper()
public interface DoctorMapper {
    DoctorMapper INSTANCE = Mappers.getMapper(DoctorMapper.class);

    @Named("toUserDTO")
    @Mapping(source = "doctor.id", target = "doctorId")
    @Mapping(source = "admin.id", target = "adminId")
    @Mapping(source = "patient.id", target = "patientId")
    UserDTO toUserDTO(User user);

    @Named("toDoctorDTO")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "hospital.id", target = "hospitalId")
    DoctorDTO toDoctorDTO(Doctor doctor);

    @Named("toDoctorWithUserDTO")
    @Mapping(source = "hospital.id", target = "hospitalId")
    @Mapping(target = "user" ,qualifiedByName = "toUserDTO")
    DoctorWithUserDTO toDoctorWithUserDTO(Doctor doctor);
}
