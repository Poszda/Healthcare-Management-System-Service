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
@Mapper()
public interface DoctorMapper {
    DoctorMapper INSTANCE = Mappers.getMapper(DoctorMapper.class);

    @Named("toUserDTO")
    @Mapping(source = "doctor.id", target = "doctorId")
    @Mapping(source = "admin.id", target = "adminId")
    @Mapping(source = "patient.id", target = "patientId")
    UserDTO toUserDTO(User user);

    @Named("toHospitalDTO")
    HospitalDTO toHospitalDTO(Hospital hospital);

    @Named("toDoctorDTO")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "hospital.id", target = "hospitalId")
    DoctorDTO toDoctorDTO(Doctor doctor);

    @Named("toDoctorWithUserAndHospitalDTO")
    @Mapping(target = "user", qualifiedByName = "toUserDTO")
    @Mapping(target = "hospital" ,qualifiedByName = "toHospitalDTO")
    DoctorWithUserAndHospitalDTO toDoctorWithUserAndHospitalDTO(Doctor doctor);

    @Named("toDoctorWithUserDTO")
    @Mapping(source = "hospital.id", target = "hospitalId")
    @Mapping(target = "user" ,qualifiedByName = "toUserDTO")
    DoctorWithUserDTO toDoctorWithUserDTO(Doctor doctor);

    @Named("toDoctorWithUserAndSpecialityDTO")
    DoctorWithUserAndSpecialityDTO toDoctorWithUserAndSpecialityDTO(Doctor doctor);

    @Named("toDoctorDTOList")
    @IterableMapping(qualifiedByName = "toDoctorDTO")
    List<DoctorDTO> toDoctorDTOList(List<Doctor> doctors);

    @Named("toDoctorWithUserAndHospitalDTOList")
    @IterableMapping(qualifiedByName = "toDoctorWithUserAndHospitalDTO")
    List<DoctorWithUserAndHospitalDTO> toDoctorWithUserAndHospitalDTOList(List<Doctor> doctor);

    @Named("toDoctorWithUserAndSpecialityDTOList")
    @IterableMapping(qualifiedByName = "toDoctorWithUserAndSpecialityDTO")
    List<DoctorWithUserAndSpecialityDTO> toDoctorWithUserAndSpecialityDTOList(List<Doctor> doctors);

}
