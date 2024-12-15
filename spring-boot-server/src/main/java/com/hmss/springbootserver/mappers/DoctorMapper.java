package com.hmss.springbootserver.mappers;

import com.hmss.springbootserver.DTOs.doctor.*;
import com.hmss.springbootserver.entities.Doctor;
import com.hmss.springbootserver.utils.models.projections.DoctorSearchProjection;
import com.hmss.springbootserver.utils.models.projections.DoctorSuggestionInfoProjection;
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

    @Named("toDoctorWithUserDTO")
    @Mapping(source = "hospital.id", target = "hospitalId")
    @Mapping(source = "user.doctor.id", target = "user.doctorId")
    DoctorWithUserDTO toDoctorWithUserDTO(Doctor doctor);

    @Named("toDoctorWithUserAndSpecialityDTO")
    DoctorWithUserAndSpecialityDTO toDoctorWithUserAndSpecialityDTO(Doctor doctor);

    @Named("toDoctorProfile")
    @Mapping(source = "profileImage", target = "profileImage")
    DoctorProfileDTO toDoctorProfileDTO(Doctor doctor, String profileImage);

    @Named("toDoctorSearchDTO")
    DoctorSearchDTO toDoctorSearchDTO(DoctorSearchProjection doctor);

    @Named("toDoctorSuggestionInfoDTO")
    DoctorSuggestionInfoDTO toDoctorSuggestionInfoDTO(DoctorSuggestionInfoProjection doctor);

    @Named("toDoctorDTOList")
    @IterableMapping(qualifiedByName = "toDoctorDTO")
    List<DoctorDTO> toDoctorDTOList(List<Doctor> doctors);

    @Named("toDoctorWithUserDTOList")
    @IterableMapping(qualifiedByName = "toDoctorWithUserDTO")
    List<DoctorWithUserDTO> toDoctorWithUserDTOList(List<Doctor> doctor);

    @Named("toDoctorWithUserAndSpecialityDTOList")
    @IterableMapping(qualifiedByName = "toDoctorWithUserAndSpecialityDTO")
    List<DoctorWithUserAndSpecialityDTO> toDoctorWithUserAndSpecialityDTOList(List<Doctor> doctors);

    @Named("toDoctorSearchDTOList")
    @IterableMapping(qualifiedByName = "toDoctorSearchDTO")
    List<DoctorSearchDTO> toDoctorSearchDTOList(List<DoctorSearchProjection> doctors);

    @Named("toDoctorSuggestionInfoDTOList")
    List<DoctorSuggestionInfoDTO> toDoctorSuggestionInfoDTOList(List<DoctorSuggestionInfoProjection> doctors);
}
