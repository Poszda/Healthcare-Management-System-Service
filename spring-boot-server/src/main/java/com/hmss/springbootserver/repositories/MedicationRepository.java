package com.hmss.springbootserver.repositories;

import com.hmss.springbootserver.DTOs.appointments.MedicationDTO;
import com.hmss.springbootserver.entities.Medication;
import com.hmss.springbootserver.entities.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MedicationRepository extends JpaRepository<Medication,Long> {

    @Query("SELECT NEW com.hmss.springbootserver.DTOs.appointments.MedicationDTO(m.id, m.diagnostic.id, m.name, m.numberOfDays, m.dose) " +
            "FROM Medication m " +
            "WHERE m.diagnostic.id IN :diagnosticsIds")
    List<MedicationDTO> findByDiagnosticIdIn(@Param("diagnosticsIds") List<Long> diagnosticsIds);

    @Query("SELECT DISTINCT m.name FROM Medication m " +
            "JOIN m.diagnostic d " +
            "JOIN d.appointment a " +
            "WHERE a.patient.id = :patientId and DATE(d.createdAt) >= :startDate AND DATE(d.createdAt) <= :endDate")
    List<String> findPatientMedicationsName(@Param("patientId") Long patientId, @Param("startDate") LocalDate startDate,@Param("endDate") LocalDate endDate);
}
