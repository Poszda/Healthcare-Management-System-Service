package com.hmss.springbootserver.repositories;

import com.hmss.springbootserver.entities.Doctor;
import com.hmss.springbootserver.entities.Hospital;
import com.hmss.springbootserver.entities.User;
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

    //you cannot fetch multiple collections (except sets) - MultipleBagFetchException
    @Query("SELECT h from Hospital h " +
            "JOIN h.procedureSet p " +
            "JOIN p.speciality s " +
            "JOIN h.doctors d " +
            "JOIN d.user u " +
            "JOIN d.speciality sd " +
            "WHERE h.county IN (:counties) AND p.id = :procedureId AND s.id = sd.id")
    List<Hospital> findHospitalOptions(@Param("counties") List<String> counties,@Param("procedureId") long procedureId);


//    @Query("SELECT h from Hospital h " +
//            "JOIN h.procedureSet p " +
//            "WHERE h.id= 1 AND p.id BETWEEN 1 AND 4")
//    Hospital findNeededProcedures();


    @Query("SELECT COUNT(a) FROM Appointment a " +
            "JOIN a.doctor.hospital h " +
            "WHERE MONTH(a.date) = :month AND YEAR(a.date) = :year AND h.id = :hospitalId")
    Long findAppointmentsNumberByMonth(@Param("hospitalId")Long hospitalId,@Param("month")int month,@Param("year")int year);

    @Query("SELECT p.birthDate as birthDate FROM Appointment a " +
            "JOIN a.patient p " +
            "JOIN a.doctor.hospital h " +
            "WHERE MONTH(a.date) = :month AND YEAR(a.date) = :year AND h.id = :hospitalId " +
            "GROUP BY a.patient")
    List<LocalDate> findPatientsAges(@Param("hospitalId")Long hospitalId,@Param("month")int month,@Param("year")int year);

    @Query("SELECT SUM(p.price) from Appointment a " +
            "JOIN a.procedure p " +
            "JOIN a.doctor.hospital h " +
            "WHERE MONTH(a.date) = :month AND YEAR(a.date) = :year AND h.id = :hospitalId ")
    Double findEstimatedEarningsByMonth(@Param("hospitalId")Long hospitalId,@Param("month")int month,@Param("year")int year);

    @Query("SELECT s.name as speciality,COUNT(a.id) as appointments from Appointment a " +
            "JOIN a.doctor d " +
            "JOIN d.hospital h " +
            "JOIN a.procedure p " +
            "JOIN p.speciality s " +
            "WHERE MONTH(a.date) = :month AND YEAR(a.date) = :year AND h.id = :hospitalId " +
            "group by s.id")
    List<SpecialityFrequencyProjection> findPatientVisitBySpeciality(@Param("hospitalId")Long hospitalId, @Param("month")int month,@Param("year")int year);


    //ql_mode=only_full_group_by changed
    //SET GLOBAL sql_mode=(SELECT REPLACE(@@sql_mode,'ONLY_FULL_GROUP_BY',''));
    @Query("SELECT FUNCTION('MONTHNAME',a.date) as month,COUNT(a) as appointments, SUM(p.price) as total from Appointment a " +
            "JOIN a.procedure p "+
            "JOIN a.doctor.hospital h " +
            "WHERE DATE(a.date) >= :startDate AND DATE(a.date) <= :endDate AND h.id = :hospitalId " +
            "GROUP BY MONTH(a.date)")
    List<AppointmentStatisticProjection> findAppointmentsLast6Months(@Param("hospitalId")Long hospitalId, @Param("startDate")LocalDate startDate,@Param("endDate")LocalDate endDate);

}


