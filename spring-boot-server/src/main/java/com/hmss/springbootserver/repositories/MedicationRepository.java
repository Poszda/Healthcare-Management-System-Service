package com.hmss.springbootserver.repositories;

import com.hmss.springbootserver.entities.Medication;
import com.hmss.springbootserver.entities.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationRepository extends JpaRepository<Medication,Long> {
}
