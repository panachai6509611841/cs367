package assignment2.appointment;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AppointmentController {

  @Autowired
  private final AppointmentRepository repository;

  AppointmentController(AppointmentRepository repository){
    this.repository = repository;
  }

  @GetMapping("/technicians")
    public List<Technician> getAllTechnicians() {
        return repository.findAll();
    }

    // 1. ค้นหาช่างจากความถนัด
    @GetMapping("/search/{expertise}")
    public List<Technician> findByExpertise(@PathVariable String expertise) {
        return repository.findByExpertiseContainingIgnoreCase(expertise);
    }

    // 2. ค้นหาช่างจากชื่อหรือไอดี
    @GetMapping("/search")
    public ResponseEntity<?> findByNameOrId(@RequestParam(required = false) Long id,
                                            @RequestParam(required = false) String name) {
        if (id != null) {
            Optional<Technician> tech = repository.findById(id);
            return tech.map(ResponseEntity::ok)
                       .orElse(ResponseEntity.notFound().build());
        } else if (name != null) {
            List<Technician> list = repository.findByNameContainingIgnoreCase(name);
            return ResponseEntity.ok(list);
        } else {
            return ResponseEntity.badRequest().body("Please provide either id or name.");
        }
    }

    @PostMapping("/{id}/book")
    public ResponseEntity<String> bookAppointment(@PathVariable Long id, @RequestBody Map<String, String> payload) {
        Optional<Technician> technicianOpt = repository.findById(id);
        if (technicianOpt.isEmpty()) return ResponseEntity.notFound().build();
    
        Technician technician = technicianOpt.get();
    
        String appointmentDate = payload.get("appointmentDate");
        String customerName = payload.get("customerName");
    
        if (appointmentDate == null || customerName == null) {
            return ResponseEntity.badRequest().body("Missing appointmentDate or customerName.");
        }
    
        List<String> dates = technician.getAppointmentDates();
        if (dates.contains(appointmentDate)) {
            return ResponseEntity.badRequest().body("Technician is already booked at " + appointmentDate);
        }
    
        technician.getAppointmentDates().add(appointmentDate);
        technician.getCustomerNames().add(customerName);
        repository.save(technician);
    
        return ResponseEntity.ok(
            "Appointment booked for customer '" + customerName +
            "' with technician '" + technician.getName() +
            "' (ID: " + technician.getId() +
            ") at " + appointmentDate
        );
    }
    

}
