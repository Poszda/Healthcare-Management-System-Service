package com.hmss.springbootserver.repositories;

import com.hmss.springbootserver.entities.Procedure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ProcedureRepository extends JpaRepository<Procedure,Long> {
    @Query("SELECT p.duration FROM Procedure p WHERE p.id=:id")
    Integer findDurationById(long id);

    @Query("SELECT p.id FROM Hospital h JOIN h.procedureSet p WHERE h.id = :hospitalId")
    List<Long> findProcedureIdsByHospitalId(@Param("hospitalId") Long hospitalId);
}
