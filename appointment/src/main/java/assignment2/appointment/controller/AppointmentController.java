package assignment2.appointment.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import assignment2.appointment.model.Technician;
import assignment2.appointment.repository.AppointmentRepository;


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
   public String findUniqueByExpertise(@PathVariable String expertise) {
    List<Technician> all = repository.findByExpertiseContainingIgnoreCase(expertise);
    Set<String> seen = new HashSet<>();

    List<Technician> unique = all.stream()
        .filter(t -> seen.add(t.getTechnicianID()))
        .collect(Collectors.toList());

    if (unique.isEmpty()) {
        return "Can't Find: " + expertise;
    }

    StringBuilder sb = new StringBuilder("[ Search results for technician ] '" + expertise + "'\n\n");
    for (Technician t : unique) {
        sb.append("   Technician ID : ").append(t.getTechnicianID()).append("\n")
          .append("   Name : ").append(t.getName()).append("\n")
          .append("   Phone : ").append(t.getPhone()).append("\n")
          .append("   Expertise : ").append(t.getExpertise()).append("\n")
          .append("   -----------------------------\n");
    }

    return sb.toString();
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
    //จอง
    @PostMapping("/{technicianID}/book")
    public ResponseEntity<String> bookAppointment(
    @PathVariable String technicianID,
    @RequestBody Map<String, String> payload
    ) {
    List<Technician> technicians = repository.findByTechnicianID(technicianID);
    if (technicians.isEmpty()) {
        return ResponseEntity.notFound().build();
    }

    // เลือก technician รายแรก หรือ implement logic เพื่อเลือกให้ถูกต้อง
    Technician technician = technicians.get(0);  // 👈 อาจใช้ logic อื่นแทน

    String appointmentDate = payload.get("appointmentDate");
    String customerName = payload.get("customerName");

    if (appointmentDate == null || customerName == null) {
        return ResponseEntity.badRequest().body("Missing appointmentDate or customerName.");
    }

    // เพิ่ม technician ใหม่เป็นรอบใหม่
    Technician newAppointment = new Technician(
        technicianID,
        technician.getName(),
        technician.getPhone(),
        technician.getExpertise(),
        appointmentDate,
        customerName
    );

    repository.save(newAppointment);

    return ResponseEntity.ok("Appointment booked for technicianID: " + technicianID + " at " + appointmentDate);
}

    

}
