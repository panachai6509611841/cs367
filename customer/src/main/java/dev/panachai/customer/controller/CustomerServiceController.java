package dev.panachai.customer.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.panachai.customer.model.Customer;
import dev.panachai.customer.repository.CustomerRepository;

@RestController
@RequestMapping("/technician-access")
public class CustomerServiceController {
    @Autowired
    private final CustomerRepository customerRepository;

    CustomerServiceController (CustomerRepository customerRepository)
    {
        this.customerRepository = customerRepository;
    }
    @DeleteMapping("/cancel-booking")
    public ResponseEntity<String> cancelBooking(@RequestParam String technicianId, @RequestParam String customerName) {
        Optional<Customer> customerOpt = customerRepository.findByCustomerName(customerName);
        if (customerOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Customer customer = customerOpt.get();

        if (!technicianId.equals(customer.getTechnicianID())) {
            return ResponseEntity.badRequest().body("Technician ID does not match customer's booking.");
        }

        customerRepository.delete(customer);

        return ResponseEntity.ok("Customer record deleted for: " + customerName);
    }



    // 2. ดูสถานที่ที่ต้องไปทำงานของลูกค้าคนหนึ่ง
    @GetMapping("/location")
    public ResponseEntity<String> getCustomerLocation(@RequestParam String customerName) {
        Optional<Customer> customerOpt = customerRepository.findByCustomerName(customerName);
        if (customerOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Customer customer = customerOpt.get();
        return ResponseEntity.ok("Customer location: " + customer.getLocation());
    }
}
