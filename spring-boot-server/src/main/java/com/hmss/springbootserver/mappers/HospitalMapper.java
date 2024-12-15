package com.hmss.springbootserver.mappers;

import com.hmss.springbootserver.DTOs.hospital.HospitalDTO;
import com.hmss.springbootserver.entities.Hospital;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Named("HospitalMapper")
@Mapper
public interface HospitalMapper {
    HospitalMapper INSTANCE = Mappers.getMapper(HospitalMapper.class);

    @Named("toHospitalDTO")
    HospitalDTO toHospitalDTO(Hospital hospital);

    @Named("toHospitalDTOList")
    @IterableMapping(qualifiedByName = "toHospitalDTO")
    List<HospitalDTO> toHospitalDTOList(List<Hospital> hospitals);


//    @Named("toHospitalWithDoctorsWithUserDTOList")
//    @IterableMapping(qualifiedByName = "toHospitalWithDoctorsWithUserDTO")
//    List<HospitalFullDTO> toHospitalWithDoctorsWithUserDTOList(List<Hospital> hospitals);
//    @Named("toHospitalWithDoctorsWithUserDTO")
//    @Mapping(target = "admin", ignore = true)
//    @Mapping(target = "procedureSet", ignore = true)
//    @Mapping(target = "doctors", qualifiedByName = "toDoctorWithUserDTO")
//    HospitalFullDTO toHospitalWithDoctorsWithUserDTO(Hospital hospital);
//
//    @Named("toDoctorWithUserDTO")
//    @Mapping(target = "appointments", ignore = true)
//    @Mapping(target = "speciality", ignore = true)
//    @Mapping(target = "hospital", ignore = true)
//    @Mapping(target = "user", qualifiedByName = "toUserWithFileDTO")
//    DoctorFullDTO toDoctorWithUserDTO(Doctor doctor);
//
//    @Named("toUserWithFileDTO")
//    @Mapping(target = "patient", ignore = true)
//    @Mapping(target = "doctor", ignore = true)
//    @Mapping(target = "admin", ignore = true)
//    @Mapping(target = "fileMetadataList", qualifiedByName = "toFileMetadataDTO")
//    UserFullDTO toUserWithFileDTO(User user);
//
//    @Named("toFileMetadataDTO")
//    @Mapping(target = "user", ignore = true)
//    FileMetadataFullDTO toFileMetadataDTO(User user);



}
