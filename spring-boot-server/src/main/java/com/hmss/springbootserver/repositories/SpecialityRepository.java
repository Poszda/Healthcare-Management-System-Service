package com.hmss.springbootserver.repositories;

import com.hmss.springbootserver.entities.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpecialityRepository extends JpaRepository<Speciality,Long> {
    @Query("SELECT DISTINCT p.speciality FROM Hospital h "+
            "JOIN h.procedureSet p " +
            "WHERE h.id = :hospitalId")
    List<Speciality> findHospitalSpecialities(@Param("hospitalId") Long hospitalId);
}
