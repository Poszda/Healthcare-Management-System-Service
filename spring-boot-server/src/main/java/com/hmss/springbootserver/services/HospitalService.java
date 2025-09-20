package com.hmss.springbootserver.services;

import com.hmss.springbootserver.entities.Hospital;
import com.hmss.springbootserver.entities.Procedure;
import com.hmss.springbootserver.exceptions.ResourceNotFoundException;
import com.hmss.springbootserver.repositories.HospitalRepository;
import com.hmss.springbootserver.repositories.ProcedureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class HospitalService {
    private final HospitalRepository hospitalRepository;
    private final ProcedureRepository procedureRepository;

    @Autowired
    public HospitalService(HospitalRepository hospitalRepository, ProcedureRepository procedureRepository) {
        this.hospitalRepository = hospitalRepository;
        this.procedureRepository = procedureRepository;
    }

    public List<String> getAllHospitalCounties(){
       return this.hospitalRepository.findAllCounties();
    }


    public void saveHospitalProcedures(Long hospitalId, List<Long> proceduresIds) {
        Optional<Hospital> hospitalOptional =  this.hospitalRepository.findById(hospitalId);
        if(hospitalOptional.isEmpty()) throw new ResourceNotFoundException("Hospital not found");

        Hospital hospital =  hospitalOptional.get();
        Set<Procedure> procedures = new HashSet<>(procedureRepository.findAllById(proceduresIds));
        hospital.setProcedureSet(procedures);

        hospitalRepository.save(hospital);
    }

}
