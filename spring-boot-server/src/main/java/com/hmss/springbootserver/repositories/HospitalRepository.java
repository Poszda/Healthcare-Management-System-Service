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
    @Query("SELECT h FROM Hospital h " +
            "JOIN h.procedureSet p " +
            "JOIN FETCH h.doctors d " + // should i also join fetch d.users ? it seems that generated even more queries???
            "JOIN d.speciality s " +
            "JOIN p.speciality sp " +
            "WHERE h.county = :county AND p.id =:procedureId AND s.id = sp.id")
    List<Hospital> findPossibleHospitalsAndDoctorsForAppointments(@Param("county") String county,@Param("procedureId") long procedureId);

    @Query("SELECT DISTINCT h.county FROM Hospital h")
    List<String> findAllCounties();


    @Query("SELECT h FROM Hospital h " +
            "JOIN FETCH h.doctors d " +
            "JOIN d.speciality s " +
            "WHERE h.county IN (:counties) AND s.id = :specialityId")
    List<Hospital> findPossibleHospitalsAndDoctorsForAppointments(@Param("counties") List<String> counties,@Param("specialityId") long specialityId);
}