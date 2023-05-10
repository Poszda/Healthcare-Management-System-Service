package com.hmss.springbootserver.repositories;

import com.hmss.springbootserver.entities.Doctor;
import com.hmss.springbootserver.utils.models.projections.DoctorProgramProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Long> {
    @Query("SELECT d.id as id,d.programStart as programStart,d.programEnd as programEnd FROM Doctor d " +
            "WHERE d.id IN :doctorsIds")
    List<DoctorProgramProjection> findDoctorsPrograms(@Param("doctorsIds") List<Long> doctorsIds);
}
