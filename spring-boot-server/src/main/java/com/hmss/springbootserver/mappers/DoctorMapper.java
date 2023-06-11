package com.hmss.springbootserver.mappers;

import com.hmss.springbootserver.DTOs.doctor.DoctorDTO;
import com.hmss.springbootserver.DTOs.doctor.DoctorWithUserAndHospitalDTO;
import com.hmss.springbootserver.DTOs.doctor.DoctorWithUserAndSpecialityDTO;
import com.hmss.springbootserver.DTOs.doctor.DoctorWithUserDTO;
import com.hmss.springbootserver.DTOs.hospital.HospitalDTO;
import com.hmss.springbootserver.DTOs.user.UserDTO;
import com.hmss.springbootserver.entities.Doctor;
import com.hmss.springbootserver.entities.Hospital;
import com.hmss.springbootserver.entities.User;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Named("DoctorMapper")
@Mapper
public interface DoctorMapper {
    DoctorMapper INSTANCE = Mappers.getMapper(DoctorMapper.class);

    @Named("toDoctorDTO")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "hospital.id", target = "hospitalId")
    DoctorDTO toDoctorDTO(Doctor doctor);

    @Named("toDoctorWithUserAndHospitalDTO")
    @Mapping(source = "user.doctor.id", target = "user.doctorId")
    DoctorWithUserAndHospitalDTO toDoctorWithUserAndHospitalDTO(Doctor doctor);

    @Named("toDoctorWithUserDTO")
    @Mapping(source = "hospital.id", target = "hospitalId")
    @Mapping(source = "user.doctor.id", target = "user.doctorId")
    DoctorWithUserDTO toDoctorWithUserDTO(Doctor doctor);

    @Named("toDoctorWithUserAndSpecialityDTO")
    DoctorWithUserAndSpecialityDTO toDoctorWithUserAndSpecialityDTO(Doctor doctor);

    @Named("toDoctorDTOList")
    @IterableMapping(qualifiedByName = "toDoctorDTO")
    List<DoctorDTO> toDoctorDTOList(List<Doctor> doctors);

    @Named("toDoctorWithUserDTOList")
    @IterableMapping(qualifiedByName = "toDoctorWithUserDTO")
    List<DoctorWithUserDTO> toDoctorWithUserDTOList(List<Doctor> doctor);
    @Named("toDoctorWithUserAndHospitalDTOList")
    @IterableMapping(qualifiedByName = "toDoctorWithUserAndHospitalDTO")
    List<DoctorWithUserAndHospitalDTO> toDoctorWithUserAndHospitalDTOList(List<Doctor> doctor);

    @Named("toDoctorWithUserAndSpecialityDTOList")
    @IterableMapping(qualifiedByName = "toDoctorWithUserAndSpecialityDTO")
    List<DoctorWithUserAndSpecialityDTO> toDoctorWithUserAndSpecialityDTOList(List<Doctor> doctors);

}
