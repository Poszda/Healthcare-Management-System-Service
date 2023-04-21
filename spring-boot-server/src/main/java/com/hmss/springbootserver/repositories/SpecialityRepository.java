package com.hmss.springbootserver.repositories;

import com.hmss.springbootserver.entities.Procedure;
import com.hmss.springbootserver.entities.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialityRepository extends JpaRepository<Speciality,Long> {
}
