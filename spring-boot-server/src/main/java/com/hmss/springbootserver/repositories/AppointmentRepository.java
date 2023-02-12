package com.hmss.springbootserver.repositories;

import com.hmss.springbootserver.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
}
