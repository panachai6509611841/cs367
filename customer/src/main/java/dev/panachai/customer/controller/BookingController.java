package dev.panachai.customer.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dev.panachai.customer.service.*;

@RestController
@RequestMapping("/customer")
public class BookingController {
     private final TechnicianClientService technicianClientService;

    public BookingController(TechnicianClientService technicianClientService) {
        this.technicianClientService = technicianClientService;
    }

    @PostMapping("/book-and-track/{techId}")
    public ResponseEntity<String> bookAndTrack(
        @PathVariable Long techId,
        @RequestBody Map<String, String> payload
    ) {
        String customerName = payload.get("customerName");
        String customerPhone = payload.get("customerPhone");
        String location = payload.get("location");
        String appointmentDate = payload.get("appointmentDate");

        String result = technicianClientService.bookAndSaveAppointment(
            techId, appointmentDate, customerName, customerPhone, location
        );

        return ResponseEntity.ok(result);
    }
}
