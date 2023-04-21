package com.hmss.springbootserver.repositories;

import com.hmss.springbootserver.entities.Diagnostic;
import com.hmss.springbootserver.entities.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiagnosticRepository extends JpaRepository<Diagnostic,Long> {
}
