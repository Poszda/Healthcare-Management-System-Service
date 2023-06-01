package com.hmss.springbootserver.repositories;

import com.hmss.springbootserver.DTOs.appointments.PatientDiagnosticExtendedDTO;
import com.hmss.springbootserver.entities.Diagnostic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiagnosticRepository extends JpaRepository<Diagnostic,Long> {

//    @Query("SELECT new com.hmss.springbootserver.DTOs.appointments.PatientDiagnosticExtendedDTO(d.id, d.name, d.description, m.id, m.name, m.numberOfDays, m.dose) " +
//            "FROM Diagnostic d " +
//            "LEFT JOIN d.medications m " +
//            "WHERE d.id = 1")
//    PatientDiagnosticExtendedDTO anExampleMethod();

// A MERS CU NESTED PROJECTION DAR A TREBUIT SA FAC UN ALT CONSTRUCTOR...HMM

//m.id, m.name, m.numberOfDays, m.dose
    @Query("SELECT new com.hmss.springbootserver.DTOs.appointments.PatientDiagnosticExtendedDTO(d.id, d.name, d.createdAt, u.firstName, u.lastName, d.description, a.date, p.name,s.name, h.name) " +
            "FROM Diagnostic d " +
            "JOIN d.appointment a " +
            "JOIN a.procedure p " +
            "JOIN a.patient pt " +
            "JOIN a.doctor dr " +
            "JOIN dr.user u " +
            "JOIN dr.speciality s " +
            "JOIN dr.hospital h " +
            "WHERE pt.id = :patientId")
    List<PatientDiagnosticExtendedDTO> findPatientDiagnosticsExtended(@Param("patientId") Long patientId);
}
