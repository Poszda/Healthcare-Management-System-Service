package com.hmss.springbootserver.repositories;

import com.hmss.springbootserver.DTOs.hospital.HospitalWithDoctorsDTO;
import com.hmss.springbootserver.entities.Hospital;
import com.hmss.springbootserver.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital,Long> {
    @Query("SELECT DISTINCT h.county FROM Hospital h")
    List<String> findAllCounties();


    @Query("SELECT h FROM Hospital h " +
            "JOIN FETCH h.doctors d " +
            "JOIN d.speciality s " +
            "WHERE h.county IN (:counties) AND s.id = :specialityId")
    List<Hospital> findPossibleHospitalsAndDoctorsForAppointments(@Param("counties") List<String> counties,@Param("specialityId") long specialityId);
}