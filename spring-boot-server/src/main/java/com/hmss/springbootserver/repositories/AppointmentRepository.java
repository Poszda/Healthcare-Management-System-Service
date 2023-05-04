package com.hmss.springbootserver.repositories;

import com.hmss.springbootserver.entities.Appointment;
import com.hmss.springbootserver.entities.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
    @Query("SELECT a FROM Appointment a " +
            "WHERE a.doctor.id IN (:doctorsIds) AND (a.date BETWEEN :startDate AND :endDate)")
    List<Appointment> findAppointmentsByDoctorsInAPeriod(@Param("doctorsIds") List<Long> doctorsIds,
                                                         @Param("startDate") LocalDateTime statDate,
                                                         @Param("endDate") LocalDateTime endDate);

}
