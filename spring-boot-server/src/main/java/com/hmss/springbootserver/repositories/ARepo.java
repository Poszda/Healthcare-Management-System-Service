package com.hmss.springbootserver.repositories;

import com.hmss.springbootserver.entities.A;
import com.hmss.springbootserver.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ARepo extends JpaRepository<A,Long> {
}
