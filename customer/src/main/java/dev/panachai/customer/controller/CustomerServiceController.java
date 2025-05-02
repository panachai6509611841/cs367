package dev.panachai.customer.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.panachai.customer.model.Customer;
import dev.panachai.customer.repository.CustomerRepository;

@RestController
@RequestMapping("/technician-access")
public class CustomerServiceController {

    private final CustomerRepository customerRepository;

    public CustomerServiceController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // 1. ยกเลิกการจองนัดของลูกค้า (โดย technicianId และ customerId)
    @DeleteMapping("/cancel-booking")
    public ResponseEntity<String> cancelBooking(@RequestParam Long technicianId, @RequestParam Long customerId) {
        Optional<Customer> customerOpt = customerRepository.findById(customerId);
        if (customerOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Customer customer = customerOpt.get();
        if (!technicianId.equals(customer.getTechnicianID())) {
            return ResponseEntity.status(403).body("You are not authorized to cancel this booking.");
        }

        customer.setAppointmentDates(null);
        customer.setTechnicianID(null);
        customerRepository.save(customer);

        return ResponseEntity.ok("Booking cancelled for customer: " + customer.getCustomerName());
    }

    // 2. ดูสถานที่ที่ต้องไปทำงานของลูกค้าคนหนึ่ง
    @GetMapping("/location/{customerId}")
    public ResponseEntity<String> getCustomerLocation(@PathVariable Long customerId) {
        Optional<Customer> customerOpt = customerRepository.findById(customerId);
        if (customerOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Customer customer = customerOpt.get();
        return ResponseEntity.ok("Customer location: " + customer.getLocation());
    }
}
