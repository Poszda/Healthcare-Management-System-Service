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


//    @Query("SELECT h FROM Hospital h " +
//            "JOIN FETCH h.doctors d " +
//            "JOIN d.speciality s " +
//            "WHERE h.county IN (:counties) AND s.id = :procedureId")
    @Query("SELECT h from Hospital h " +
            "JOIN h.procedureSet p " +
            "JOIN p.speciality s " +
            "JOIN FETCH h.doctors d " +
            "JOIN d.speciality sd " +
            "WHERE h.county IN (:counties) AND p.id = :procedureId AND s.id = sd.id")
    List<Hospital> findPossibleHospitalsAndDoctorsForAppointments(@Param("counties") List<String> counties,@Param("procedureId") long procedureId);
}