package com.hmss.springbootserver.repositories;

import com.hmss.springbootserver.entities.Appointment;
import com.hmss.springbootserver.utils.models.projections.AppointmentCardProjection;
import com.hmss.springbootserver.utils.models.projections.DoctorAppointmentProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.print.Doc;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
    @Query("SELECT a FROM Appointment a " +
            "WHERE a.doctor.id IN (:doctorsIds) AND (a.date BETWEEN :startDate AND :endDate) " +
            "ORDER BY a.date ASC")
    List<Appointment> findAppointmentsByDoctorsInAPeriod(@Param("doctorsIds") List<Long> doctorsIds,
                                                         @Param("startDate") LocalDateTime startDate,
                                                         @Param("endDate") LocalDateTime endDate);
    @Query("SELECT a.id as id, a.date as dateTime, u.firstName as doctorFirstName, u.lastName as doctorLastName, " +
            "p.name as procedureName, p.price as price, p.duration as duration, s.name as doctorSpeciality,h.name as hospitalName, di.id as diagnosticId " +
            "FROM Appointment a " +
            "JOIN a.doctor d "+
            "JOIN d.user u " +
            "JOIN a.procedure p "+
            "JOIN d.speciality s "+
            "JOIN d.hospital h "+
            "LEFT JOIN a.diagnostic di "+
            "WHERE a.patient.id =:patientId")
    List<AppointmentCardProjection> findUserAppointmentsWidgets(@Param("patientId") Long patientId);

    @Query("SELECT a.id as id, a.date as date, p.name as procedureName, " +
            "p.duration as duration, pa.id as patientId, pa.phone as phone, pa.age as age, u.firstName as firstName, u.lastName as lastName, d.id as diagnosticId " +
            "FROM Appointment a " +
            "JOIN a.procedure p " +
            "LEFT JOIN a.diagnostic d " +
            "JOIN a.patient pa " +
            "JOIN pa.user u " +
            "WHERE a.doctor.id =:doctorId")
    List<DoctorAppointmentProjection> findDoctorAppointments(@Param("doctorId") Long doctorId);

    @Query("SELECT COALESCE(SUM(p.duration),0) FROM Appointment a " +
            "JOIN a.procedure p " +
            "WHERE FUNCTION('DATE', a.date) = CURRENT_DATE AND a.doctor.id = :doctorId")
    int getSumOfTodayProcedureDuration(@Param("doctorId") Long doctorId);
}
