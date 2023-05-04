package com.hmss.springbootserver.services;

import com.hmss.springbootserver.DTOs.hospital.HospitalWithDoctorsDTO;
import com.hmss.springbootserver.DTOs.speciality.SpecialityWithProceduresDTO;
import com.hmss.springbootserver.entities.Speciality;
import com.hmss.springbootserver.mappers.HospitalMapper;
import com.hmss.springbootserver.mappers.SpecialityMapper;
import com.hmss.springbootserver.repositories.SpecialityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SpecialityService {
    private final SpecialityRepository specialityRepository;

    @Autowired
    public SpecialityService(SpecialityRepository specialityRepository) {
        this.specialityRepository = specialityRepository;
    }
    public List<SpecialityWithProceduresDTO> getSpecialitiesWithProcedures(){
        var list = this.specialityRepository.findAll();
        return SpecialityMapper.INSTANCE.toSpecialityWithProceduresDTOList(list);
    }

}
