package dev.panachai.customer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.panachai.customer.model.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long>{
    List<Appointment> findByCustomerId(Long customerId);
    List<Appointment> findByTechnicianId(Long technicianId);
}
