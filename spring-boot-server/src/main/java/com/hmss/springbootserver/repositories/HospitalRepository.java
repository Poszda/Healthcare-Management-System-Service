package com.hmss.springbootserver.repositories;

import com.hmss.springbootserver.entities.Hospital;
import com.hmss.springbootserver.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalRepository  extends JpaRepository<Hospital,Long> {
}
