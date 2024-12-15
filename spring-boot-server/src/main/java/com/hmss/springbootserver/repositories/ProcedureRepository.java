package com.hmss.springbootserver.repositories;

import com.hmss.springbootserver.entities.Procedure;
import com.hmss.springbootserver.utils.models.projections.ProcedureCounterProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProcedureRepository extends JpaRepository<Procedure,Long> {
    @Query("SELECT p.duration FROM Procedure p WHERE p.id=:id")
    Integer findDurationById(long id);

    @Query("SELECT p.id FROM Hospital h JOIN h.procedureSet p WHERE h.id = :hospitalId")
    List<Long> findProcedureIdsByHospitalId(@Param("hospitalId") Long hospitalId);

    @Query("SELECT  p.id as id, p.name as name, count(p.id) as total FROM Procedure p " +
            "JOIN p.appointments a " +
            "JOIN a.doctor d " +
            "WHERE d.id = :doctorId AND DATE(a.date) >= :startDate AND DATE(a.date) <= :endDate " +
            "GROUP BY p.id")
    List<ProcedureCounterProjection> countDoctorInterventionsByProcedure(@Param("doctorId") Long doctorId, @Param("startDate")LocalDate startDate, @Param("endDate") LocalDate endDate);
}
