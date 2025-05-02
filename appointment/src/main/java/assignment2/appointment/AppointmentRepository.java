package assignment2.appointment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Technician,Long>{
  List<Technician> findByExpertiseContainingIgnoreCase(String expertise);
  List<Technician> findByNameContainingIgnoreCase(String name);
  List<Technician> findByTechnicianID(String technicianID);
}
