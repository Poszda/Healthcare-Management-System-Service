package com.hmss.springbootserver.repositories;

import com.hmss.springbootserver.entities.Doctor;
import com.hmss.springbootserver.entities.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Long> {
}
