package com.hmss.springbootserver.repositories;

import com.hmss.springbootserver.entities.Appointment;
import com.hmss.springbootserver.entities.Hospital;
import com.hmss.springbootserver.utils.models.projections.AppointmentWidgetProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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


    @Query("SELECT a.id as id, a.date as date, u.firstName as firstName, u.lastName as lastName, " +
            "p.name as procedureName, p.price as price, p.duration as duration, s.name as doctorSpeciality " +
            "FROM Appointment a " +
            "JOIN a.doctor d "+
            "JOIN d.user u " +
            "JOIN a.procedure p "+
            "JOIN d.speciality s "+
            "JOIN d.hospital h "+
            "WHERE a.patient.id =:patientId")
    List<AppointmentWidgetProjection> findUserAppointmentsWidgets(@Param("patientId") Long patientId);
}
