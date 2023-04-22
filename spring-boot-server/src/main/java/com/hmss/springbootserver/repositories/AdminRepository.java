package com.hmss.springbootserver.repositories;

import com.hmss.springbootserver.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository  extends JpaRepository<Admin,Long> {
}
