package assignment2.appointment.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import assignment2.appointment.client.TechnicianClientService;
import assignment2.appointment.repository.AppointmentRepository;

@RestController
@RequestMapping("/delete")
public class DeleteBookingController {

    private final TechnicianClientService technicianClientService;
    private final AppointmentRepository technicianRepo;

    public DeleteBookingController(TechnicianClientService technicianClientService,
                                    AppointmentRepository technicianRepo) {
        this.technicianClientService = technicianClientService;
        this.technicianRepo = technicianRepo;
    }

    @DeleteMapping("/cancel-booking-tech")
    public ResponseEntity<String> cancelBooking(@RequestParam String technicianId,
                                                @RequestParam String customerName) {
        // Step 1: เรียกให้ลูกค้าลบตัวเอง
        String responseFromCustomer = technicianClientService.cancelCustomerBooking(technicianId, customerName);

        // Step 2: ลบตัวเอง (ลบ technician record ที่มี technicianId และ customerName ตรงกัน)
        technicianRepo.deleteByTechnicianIDAndCustomerName(technicianId, customerName);

        return ResponseEntity.ok("" + responseFromCustomer);
    }
}


