package com.hmss.springbootserver.repositories;

import com.hmss.springbootserver.entities.Hospital;
import com.hmss.springbootserver.utils.models.projections.AppointmentStatisticProjection;
import com.hmss.springbootserver.utils.models.projections.SpecialityFrequencyProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital,Long> {
    @Query("SELECT DISTINCT h.county FROM Hospital h")
    List<String> findAllCounties();

    @Query("SELECT h from Hospital h " +
            "JOIN h.procedureSet p " +
            "JOIN p.speciality s " +
            "JOIN FETCH h.doctors d " +
            "JOIN d.speciality sd " +
            "WHERE h.county IN (:counties) AND p.id = :procedureId AND s.id = sd.id")
    List<Hospital> findPossibleHospitalsAndDoctorsForAppointments(@Param("counties") List<String> counties,@Param("procedureId") long procedureId);


    @Query("SELECT COUNT(a) FROM Appointment a " +
            "JOIN a.doctor.hospital h " +
            "WHERE FUNCTION('MONTH', a.date) = :month AND h.id = :hospitalId")
    Long findAppointmentsNumberByMonth(@Param("hospitalId")Long hospitalId,@Param("month")int month);

    @Query(" SELECT AVG(age) FROM " +
            "(SELECT p.age as age FROM Appointment a " +
            "JOIN a.patient p " +
            "JOIN a.doctor.hospital h " +
            "WHERE FUNCTION('MONTH', a.date) = :month AND h.id = :hospitalId " +
            "GROUP BY a.patient)")
    Double findAveragePatientAgeByMonth(@Param("hospitalId")Long hospitalId,@Param("month")int month);

    @Query("SELECT SUM(p.price) from Appointment a " +
            "JOIN a.procedure p " +
            "JOIN a.doctor.hospital h " +
            "WHERE FUNCTION('MONTH', a.date) = :month AND h.id = :hospitalId ")
    Double findEstimatedEarningsByMonth(@Param("hospitalId")Long hospitalId,@Param("month")int month);

//    @Query("SELECT s.name as speciality,COUNT(a.id) as appointments from Appointment a " +
//            "JOIN a.doctor d " +
//            "JOIN d.hospital h " +
//            "JOIN a.procedure p " +
//            "RIGHT JOIN p.speciality s " +
//            "WHERE (FUNCTION('MONTH', a.date) = :month AND h.id = :hospitalId) OR h.id IS NULL " +
//            "group by s.id, s.name")
    @Query("SELECT s.name as speciality,COUNT(a.id) as appointments from Appointment a " +
            "JOIN a.doctor d " +
            "JOIN d.hospital h " +
            "JOIN a.procedure p " +
            "JOIN p.speciality s " +
            "WHERE FUNCTION('MONTH', a.date) = :month AND h.id = :hospitalId " +
            "group by s.id")
    List<SpecialityFrequencyProjection> findPatientVisitBySpeciality(@Param("hospitalId")Long hospitalId, @Param("month")int month);

    @Query("SELECT FUNCTION('MONTHNAME',a.date) as month,COUNT(a) as appointments, SUM(p.price) as total from Appointment a " +
            "JOIN a.procedure p "+
            "JOIN a.doctor.hospital h " +
            "WHERE FUNCTION('DATE',a.date) >= :startDate AND FUNCTION('MONTH', a.date) <= FUNCTION('MONTH', CURRENT_DATE) AND h.id = :hospitalId " +
            "GROUP BY FUNCTION('MONTH',a.date)")
    List<AppointmentStatisticProjection> findAppointmentsLast6Months(@Param("hospitalId")Long hospitalId, @Param("startDate")LocalDate startDate);

}


