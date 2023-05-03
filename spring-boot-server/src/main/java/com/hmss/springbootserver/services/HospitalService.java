package com.hmss.springbootserver.services;

import com.hmss.springbootserver.repositories.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospitalService {
    private final HospitalRepository hospitalRepository;

    @Autowired
    public HospitalService(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    public List<String> getAllHospitalCounties(){
       return this.hospitalRepository.findAllCounties();
    }


}
