package com.hmss.springbootserver.repositories;

import com.hmss.springbootserver.entities.Procedure;
import com.hmss.springbootserver.entities.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SpecialityRepository extends JpaRepository<Speciality,Long> {

}
