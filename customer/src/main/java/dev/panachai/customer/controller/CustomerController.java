package dev.panachai.customer.controller;

import org.springframework.web.bind.annotation.RestController;

import dev.panachai.customer.dto.BookingRequest;
import dev.panachai.customer.model.Appointment;
import dev.panachai.customer.service.AppointmentService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/customer")
public class CustomerController {
    
    private final AppointmentService appointmentService;
    public CustomerController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }
    // POST /customer/book-slot
    @PostMapping("/book-slot")
    public ResponseEntity<Appointment> bookSlot(@RequestBody BookingRequest request) {
        return ResponseEntity.ok(appointmentService.bookAppointment(request));
    }

    // GET /customer/my-appointments?customerId=1
    @GetMapping("/my-appointments")
    public ResponseEntity<List<Appointment>> getAppointments(@RequestParam Long customerId) {
        return ResponseEntity.ok(appointmentService.getAppointmentsByCustomer(customerId));
    }

    // GET /customer/appointments-for-tech?technicianId=4
    @GetMapping("/appointments-for-tech")
    public ResponseEntity<List<Appointment>> getAppointmentsForTech(@RequestParam Long technicianId) {
        return ResponseEntity.ok(appointmentService.getAppointmentsForTechnician(technicianId));
    }

    
}
