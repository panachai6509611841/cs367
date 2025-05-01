package assignment2.appointment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


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

  @GetMapping("/{id}/available-slots")
    public ResponseEntity<List<LocalDateTime>> getAvailableSlots(@PathVariable Long id) {
        Optional<Technician> technicianOpt = repository.findById(id);
        if (technicianOpt.isEmpty()) return ResponseEntity.notFound().build();

        Technician technician = technicianOpt.get();
        List<LocalDateTime> availableSlots = new ArrayList<>();

        LocalDate today = LocalDate.now();
        for (int day = 0; day < 7; day++) {
            LocalDate date = today.plusDays(day);
            for (int hour = 9; hour < 17; hour++) {
                LocalDateTime slot = date.atTime(hour, 0);
                if (!slot.equals(technician.getAppointmentDate())) {
                    availableSlots.add(slot);
                }
            }
        }
        return ResponseEntity.ok(availableSlots);
    }
  
  @GetMapping("/{id}/schedule")
    public ResponseEntity<Map<String, Object>> getTechnicianSchedule(@PathVariable Long id) {
        Optional<Technician> technicianOpt = repository.findById(id);
        if (technicianOpt.isEmpty()) return ResponseEntity.notFound().build();

        Technician technician = technicianOpt.get();
        Map<String, Object> schedule = new HashMap<>();
        schedule.put("technician", technician.getName());
        schedule.put("appointment", technician.getAppointmentDate());
        schedule.put("status", technician.getStatus());

        return ResponseEntity.ok(schedule);
    }
    
  @PostMapping("/{id}/book")
    public ResponseEntity<String> bookAppointment(@PathVariable Long id, @RequestBody Map<String, String> payload) {
        Optional<Technician> technicianOpt = repository.findById(id);
        if (technicianOpt.isEmpty()) return ResponseEntity.notFound().build();

        Technician technician = technicianOpt.get();
        String requestedDate = payload.get("appointmentDate");


        // ตรวจสอบว่าช่างยังไม่มีนัดที่เวลานั้น
        if (requestedDate.equals(technician.getAppointmentDate())) {
            return ResponseEntity.badRequest().body("Technician already booked at this time.");
        }

        technician.setAppointmentDate(requestedDate);
        technician.setStatus("BOOKED");
        repository.save(technician);

        return ResponseEntity.ok("Appointment booked successfully.");
    }

    @PutMapping("/{id}/reschedule")
      public ResponseEntity<String> rescheduleAppointment(@PathVariable Long id, @RequestBody Map<String, String> payload) {
      Optional<Technician> technicianOpt = repository.findById(id);
      if (technicianOpt.isEmpty()) return ResponseEntity.notFound().build();

      Technician technician = technicianOpt.get();
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
      LocalDateTime newDate = LocalDateTime.parse(payload.get("newAppointmentDate"), formatter);
      String formattedDate = newDate.format(formatter);

      if (formattedDate.equals(technician.getAppointmentDate())) {
          return ResponseEntity.badRequest().body("New appointment date is the same as current one.");
      }

      technician.setAppointmentDate(formattedDate);
      technician.setStatus("BOOKED");
      repository.save(technician);

      return ResponseEntity.ok("Appointment rescheduled to " + formattedDate);
  } 

  @PutMapping("/{id}/cancel")
    public ResponseEntity<String> cancelAppointment(@PathVariable Long id) {
      Optional<Technician> technicianOpt = repository.findById(id);
      if (technicianOpt.isEmpty()) return ResponseEntity.notFound().build();
  
      Technician technician = technicianOpt.get();
      
      // ถ้ายังไม่มีการนัดหมาย
      if (technician.getAppointmentDate() == null || technician.getStatus().equalsIgnoreCase("AVAILABLE")) {
          return ResponseEntity.badRequest().body("Technician has no active appointment to cancel.");
      }
  
      // ยกเลิกนัดหมาย
      technician.setAppointmentDate(null);
      technician.setStatus("AVAILABLE");
      repository.save(technician);
  
      return ResponseEntity.ok("Appointment cancelled successfully.");
  }
  
}
