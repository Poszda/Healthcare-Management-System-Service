package com.hmss.springbootserver.repositories;

import com.hmss.springbootserver.entities.Doctor;
import com.hmss.springbootserver.entities.Patient;
import com.hmss.springbootserver.utils.models.projections.AppointmentStatisticProjection;
import com.hmss.springbootserver.utils.models.projections.DoctorAppointmentProjection;
import com.hmss.springbootserver.utils.models.projections.DoctorPatientsVisitsProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {
    @Query("SELECT COUNT(DISTINCT p.id) as patients,MONTH(a.date) as month, YEAR(a.date) as year from Patient p " +
            "JOIN p.appointments a " +
            "JOIN a.doctor d " +
            "WHERE d.id = :doctorId AND " +
            "DATE(a.date) >= :startDate AND DATE(a.date) <= :endDate AND " +
            "FUNCTION('TIMESTAMPDIFF',YEAR, p.birthDate, CURRENT_DATE) >= :ageStart AND FUNCTION('TIMESTAMPDIFF',YEAR, p.birthDate, CURRENT_DATE) <= :ageEnd " +
            "GROUP BY MONTH(a.date) " +
            "ORDER BY year, month")
    List<DoctorPatientsVisitsProjection> countDoctorPatientsVisitsLast6Months(@Param("doctorId") Long doctorId,@Param("startDate") LocalDate startDate,@Param("endDate") LocalDate endDate,@Param("ageStart") int ageStart, @Param("ageEnd") int ageEnd);

    @Query("SELECT p FROM Patient p JOIN FETCH p.user.fileMetadataList f WHERE p.id = :patientId AND f.type = 'PROFILE_IMAGE'")
    Optional<Patient> findPatientAndProfileImage(@Param("patientId") long patientId);
}
