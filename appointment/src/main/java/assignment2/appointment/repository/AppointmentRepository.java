package assignment2.appointment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import assignment2.appointment.model.Technician;
import jakarta.transaction.Transactional;

public interface AppointmentRepository extends JpaRepository<Technician,Long>{
  List<Technician> findByExpertiseContainingIgnoreCase(String expertise);
  List<Technician> findByNameContainingIgnoreCase(String name);
  List<Technician> findByTechnicianID(String technicianID);
  @Modifying
  @Transactional
  @Query("DELETE FROM Technician t WHERE t.technicianID = :technicianID AND t.customerName = :customerName")
  void deleteByTechnicianIDAndCustomerName(String technicianID, String customerName);

}
