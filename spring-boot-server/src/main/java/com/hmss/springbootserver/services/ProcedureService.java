package com.hmss.springbootserver.services;

import com.hmss.springbootserver.repositories.ProcedureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcedureService {
    private final ProcedureRepository procedureRepository;

    @Autowired
    public ProcedureService(ProcedureRepository procedureRepository) {
        this.procedureRepository = procedureRepository;
    }

    public List<Long> getProceduresIdsByHospitalId(Long hospitalId) {
        return this.procedureRepository.findProcedureIdsByHospitalId(hospitalId);
    }
}
