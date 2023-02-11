package com.hmss.springbootserver.repositories;

import com.hmss.springbootserver.entities.Admin;
import com.hmss.springbootserver.entities.B;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BRepo extends JpaRepository<B,Long> {
    B findByName(String name);
}
