package com.hmss.springbootserver.repositories;

import com.hmss.springbootserver.entities.Doctor;
import com.hmss.springbootserver.utils.models.projections.DoctorProgramProjection;
import com.hmss.springbootserver.utils.models.projections.DoctorSearchProjection;
import com.hmss.springbootserver.utils.models.projections.DoctorSuggestionInfoProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Long> {
    @Query("SELECT d.id as id,d.programStart as programStart,d.programEnd as programEnd FROM Doctor d " +
            "WHERE d.id IN :doctorsIds")
    List<DoctorProgramProjection> findDoctorsPrograms(@Param("doctorsIds") List<Long> doctorsIds);

    @Query("SELECT d from Doctor d " +
            "JOIN FETCH d.speciality s " +
            "JOIN FETCH d.user u " +
            "WHERE d.hospital.id =:hospitalId")
    List<Doctor> findHospitalDoctorsWithSpeciality(@Param("hospitalId") Long hospitalId);

    @Query("SELECT d.id as id, u.firstName as firstName, u.lastName as lastName, f.path as profileImage " +
            "FROM Doctor d " +
            "JOIN d.user u " +
            "LEFT JOIN u.fileMetadataList f ON f.user.id = u.id AND f.type = 'PROFILE_IMAGE' "+
            "WHERE d.speciality.id = :specialityId AND " +
            "(:name IS NULL OR (u.firstName LIKE CONCAT('%', :name, '%') OR u.lastName LIKE CONCAT('%', :name, '%')))"
    )
    List<DoctorSearchProjection> findSearchedDoctors(@Param("name") String name, @Param("specialityId") Long specialityId);

    @Query("SELECT d from Doctor d " +
            "JOIN d.hospital h " +
            "JOIN FETCH d.user u " +
            "JOIN d.speciality s " +
            "JOIN s.procedures p " +
            "WHERE h.id IN (:hospitalsIds) AND p.id = :procedureId")
    List<Doctor> findDoctorOptions(@Param("hospitalsIds") List<Long> hospitalsIds,@Param("procedureId") long procedureId);

    @Query("SELECT d.id as id, u.firstName as firstName, u.lastName as lastName, s.name as speciality, h.name as hospital, f.path as profileImage " +
            "FROM Doctor d " +
            "JOIN d.hospital h " +
            "JOIN d.speciality s " +
            "JOIN d.user u " +
            "LEFT JOIN u.fileMetadataList f ON f.user.id = u.id AND f.type = 'PROFILE_IMAGE' " +
            "WHERE d.id IN (:doctorsIds)")
    List<DoctorSuggestionInfoProjection> findDoctorsSuggestionInfo(@Param("doctorsIds") List<Long> doctorsIds);
}
