package com.hmss.springbootserver.mappers;

import com.hmss.springbootserver.DTOs.doctor.DoctorDTO;
import com.hmss.springbootserver.DTOs.hospital.HospitalDTO;
import com.hmss.springbootserver.DTOs.hospital.HospitalWithDoctorsDTO;
import com.hmss.springbootserver.entities.Doctor;
import com.hmss.springbootserver.entities.Hospital;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Named("HospitalMapper")
@Mapper(uses = DoctorMapper.class)
public interface HospitalMapper {
    HospitalMapper INSTANCE = Mappers.getMapper(HospitalMapper.class);

    @Named("toHospitalDTO")
    HospitalDTO toHospitalDTO(Hospital hospital);

    @Named("toHospitalWithDoctorsDTO")
    @Mapping(source = "doctors", target = "doctors",qualifiedByName = "toDoctorWithUserDTO")
    HospitalWithDoctorsDTO toHospitalWithDoctorsDTO(Hospital hospital); // cum stie ce sa foloseasca

    @Named("toHospitalWithDoctorsDTOList")
    @IterableMapping(qualifiedByName = "toHospitalWithDoctorsDTO")
    List<HospitalWithDoctorsDTO> toHospitalWithDoctorsDTOList(List<Hospital> hospitals);

//    @Mapping(source = "hospital.id", target = "hospitalId")
//    @Mapping(source = "user.id", target = "userId")
//    DoctorDTO toDoctorDTO(Doctor doctor);

}
