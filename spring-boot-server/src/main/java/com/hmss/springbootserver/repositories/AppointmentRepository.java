package com.hmss.springbootserver.repositories;

import com.hmss.springbootserver.entities.Appointment;
import com.hmss.springbootserver.utils.models.projections.AppointmentCardProjection;
import com.hmss.springbootserver.utils.models.projections.AppointmentNextProjection;
import com.hmss.springbootserver.utils.models.projections.DoctorAppointmentProjection;
import com.hmss.springbootserver.utils.models.projections.DoctorAppointmentsCounterByStatusProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.print.Doc;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
    @Query("SELECT a FROM Appointment a " +
            "WHERE a.doctor.id IN (:doctorsIds) AND (a.date BETWEEN :startDate AND :endDate) " +
            "ORDER BY a.date ASC")
    List<Appointment> findAppointmentsByDoctorsInAPeriod(@Param("doctorsIds") List<Long> doctorsIds,
                                                         @Param("startDate") LocalDateTime startDate,
                                                         @Param("endDate") LocalDateTime endDate);
    @Query("SELECT a.id as id, a.date as dateTime, u.firstName as doctorFirstName, u.lastName as doctorLastName, d.id as doctorId, " +
            "p.name as procedureName, p.price as price, p.duration as duration, s.name as doctorSpeciality,h.name as hospitalName, di.id as diagnosticId, f.path as profileImage " +
            "FROM Appointment a " +
            "JOIN a.doctor d "+
            "JOIN d.user u " +
            "JOIN a.procedure p "+
            "JOIN d.speciality s "+
            "JOIN d.hospital h "+
            "LEFT JOIN a.diagnostic di "+
            "LEFT JOIN (SELECT fm.user.id as userId, fm.path as path FROM FileMetadata fm WHERE fm.type = 'PROFILE_IMAGE') f ON u.id = f.userId " +
            "WHERE a.patient.id =:patientId")
    List<AppointmentCardProjection> findUserAppointmentsWidgets(@Param("patientId") Long patientId);

    @Query("SELECT a.id as id, a.date as date, p.name as procedureName, " +
            "p.duration as duration, pa.id as patientId, pa.phone as phone, pa.birthDate as birthDate, u.firstName as firstName, u.lastName as lastName, d.id as diagnosticId, f.path as profileImage " +
            "FROM Appointment a " +
            "JOIN a.procedure p " +
            "LEFT JOIN a.diagnostic d " +
            "JOIN a.patient pa " +
            "JOIN pa.user u " +
            "LEFT JOIN (SELECT fm.user.id as userId, fm.path as path FROM FileMetadata fm WHERE fm.type = 'PROFILE_IMAGE') f ON u.id = f.userId " +
            "WHERE a.doctor.id =:doctorId")
    List<DoctorAppointmentProjection> findDoctorAppointments(@Param("doctorId") Long doctorId);

    @Query("SELECT COALESCE(SUM(p.duration),0) FROM Appointment a " +
            "JOIN a.procedure p " +
            "WHERE DATE(a.date) = CURRENT_DATE AND a.doctor.id = :doctorId")
    int getSumOfTodayProcedureDuration(@Param("doctorId") Long doctorId);

    @Query("SELECT " +
            "CASE " +
            "WHEN a.date > CURRENT_TIMESTAMP() THEN 'Upcoming' " +
            "WHEN a.date < CURRENT_TIMESTAMP() AND d.id IS NULL THEN 'In progress' " +
            "ELSE 'Reviewed' " +
            "END AS status, " +
            "COUNT(DISTINCT a.id) AS counter " +
            "FROM Appointment a " +
            "LEFT JOIN a.diagnostic d " +
            "JOIN a.doctor dr " +
            "WHERE dr.id = :doctorId AND MONTH(a.date) = MONTH(CURRENT_DATE) AND YEAR(a.date) = YEAR(CURRENT_DATE) "+
            "GROUP BY status")
    List<DoctorAppointmentsCounterByStatusProjection> countDoctorAppointmentsByStatus(@Param("doctorId") Long doctorId);

    @Query("SELECT a.id as id, a.date as dateTime, u.firstName as firstName, u.lastName as lastName, p.id as otherId, pr.duration as duration " +
            "FROM Appointment a " +
            "JOIN a.patient p " +
            "JOIN a.doctor d " +
            "JOIN p.user u " +
            "JOIN a.procedure pr " +
            "WHERE d.id = :doctorId AND DATE(a.date) = CURRENT_DATE AND a.date > :dateTime " +
            "ORDER BY a.date " +
            "LIMIT :limit ")
    List<AppointmentNextProjection> findDoctorTodayAppointments(@Param("doctorId") Long doctorId, @Param("dateTime") LocalDateTime dateTime, @Param("limit") int limit);

    @Query("SELECT a.id as id, a.date as dateTime, u.firstName as firstName, u.lastName as lastName, d.id as otherId, pr.duration as duration, f.path as profileImage " +
            "FROM Appointment a " +
            "JOIN a.patient p " +
            "JOIN a.doctor d " +
            "JOIN d.user u " +
            "JOIN a.procedure pr " +
            "JOIN u.fileMetadataList f " +
            "WHERE p.id = :patientId AND a.date > :dateTime AND f.type = 'PROFILE_IMAGE' " +
            "ORDER BY a.date " +
            "LIMIT :limit ")
    List<AppointmentNextProjection> findPatientNextAppointments(@Param("patientId") Long patientId, @Param("dateTime") LocalDateTime dateTime, @Param("limit") int limit);
}
