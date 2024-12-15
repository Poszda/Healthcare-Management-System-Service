package com.hmss.springbootserver.services;

import com.hmss.springbootserver.DTOs.speciality.SpecialityDTO;
import com.hmss.springbootserver.DTOs.speciality.SpecialityWithProceduresDTO;
import com.hmss.springbootserver.mappers.SpecialityMapper;
import com.hmss.springbootserver.repositories.SpecialityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialityService {
    private final SpecialityRepository specialityRepository;

    @Autowired
    public SpecialityService(SpecialityRepository specialityRepository) {
        this.specialityRepository = specialityRepository;
    }

    public List<SpecialityDTO> getSpecialities() {
        return SpecialityMapper.INSTANCE.toSpecialityDTOList(specialityRepository.findAll());
    }

    public List<SpecialityWithProceduresDTO> getSpecialitiesWithProcedures(){
        var list = this.specialityRepository.findAll();
        return SpecialityMapper.INSTANCE.toSpecialityWithProceduresDTOList(list);
    }

    public List<SpecialityDTO> getHospitalSpecialities(Long hospitalId) {
        return SpecialityMapper.INSTANCE.toSpecialityDTOList(this.specialityRepository.findHospitalSpecialities(hospitalId));
    }

}
