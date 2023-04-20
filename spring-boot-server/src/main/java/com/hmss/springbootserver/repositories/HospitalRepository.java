package com.hmss.springbootserver.repositories;

import com.hmss.springbootserver.entities.Hospital;
import com.hmss.springbootserver.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRepository  extends JpaRepository<Hospital,Long> {
}
