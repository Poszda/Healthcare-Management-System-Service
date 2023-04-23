package com.hmss.springbootserver.repositories;

import com.hmss.springbootserver.entities.Procedure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcedureRepository extends JpaRepository<Procedure,Long> {
}