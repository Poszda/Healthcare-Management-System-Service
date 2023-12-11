package com.hmss.springbootserver.repositories;

import com.hmss.springbootserver.DTOs.appointments.PatientDiagnosticExtendedDTO;
import com.hmss.springbootserver.entities.Diagnostic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DiagnosticRepository extends JpaRepository<Diagnostic,Long> {

//    @Query("SELECT new com.hmss.springbootserver.DTOs.appointments.PatientDiagnosticExtendedDTO(d.id, d.name, d.description, m.id, m.name, m.numberOfDays, m.dose) " +
//            "FROM Diagnostic d " +
//            "LEFT JOIN d.medications m " +
//            "WHERE d.id = 1")
//    PatientDiagnosticExtendedDTO anExampleMethod();

// A MERS CU NESTED PROJECTION DAR A TREBUIT SA FAC UN ALT CONSTRUCTOR...HMM

    @Query("SELECT new com.hmss.springbootserver.DTOs.appointments.PatientDiagnosticExtendedDTO(d.id, d.name, d.createdAt, u.firstName, u.lastName, dr.id, f.path, d.description, a.date, p.name,s.name, h.name) " +
            "FROM Diagnostic d " +
            "JOIN d.appointment a " +
            "JOIN a.procedure p " +
            "JOIN a.patient pt " +
            "JOIN a.doctor dr " +
            "JOIN dr.user u " +
            "JOIN dr.speciality s " +
            "JOIN dr.hospital h " +
            "LEFT JOIN (SELECT fm.user.id as userId, fm.path as path FROM FileMetadata fm WHERE fm.type = 'PROFILE_IMAGE') f ON u.id = f.userId " +
            "WHERE pt.id = :patientId")
    List<PatientDiagnosticExtendedDTO> findPatientDiagnosticsExtended(@Param("patientId") Long patientId);

    @Query("SELECT DISTINCT d.name FROM Diagnostic d " +
            "INNER JOIN d.appointment a " +
            "WHERE a.patient.id = :patientId and DATE(d.createdAt) >= :startDate AND DATE(d.createdAt) <= CURRENT_DATE")
    List<String> findPatientDiagnosticsName(@Param("patientId") Long patientId, @Param("startDate") LocalDate startDate);
}
