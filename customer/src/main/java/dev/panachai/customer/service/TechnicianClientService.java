package dev.panachai.customer.service;

import java.util.List;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import dev.panachai.customer.repository.*;
import dev.panachai.customer.model.*;

@Service
public class TechnicianClientService {
    

    private final RestTemplate rest ;
    private final CustomerRepository customerRepo;

    public TechnicianClientService(RestTemplate rest, CustomerRepository customerRepo) {
        this.rest = rest;
        this.customerRepo = customerRepo;
    }

    public List<Map<String, Object>> getAllTechnicians() {
    String url = "http://localhost:8081/technicians";

    ResponseEntity<List<Map<String, Object>>> response = rest.exchange(
        url,
        HttpMethod.GET,
        null,
        new ParameterizedTypeReference<List<Map<String, Object>>>() {}
    );

    return response.getBody();
}

public List<Map<String, Object>> searchByExpertise(String expertise) {
    String url = "http://localhost:8081/search/" + expertise;
    ResponseEntity<List<Map<String, Object>>> response = rest.exchange(
        url,
        HttpMethod.GET,
        null,
        new ParameterizedTypeReference<List<Map<String, Object>>>() {}
    );
    return response.getBody();
}
public String bookAndSaveAppointment(Long techId, String date, String customerName, String customerPhone, String location) {
    // Step 1: เรียก Technician Service เพื่อจอง
    String url = "http://localhost:8081/" + techId + "/book";
    Map<String, String> payload = Map.of(
        "appointmentDate", date,
        "customerName", customerName
    );
    String response = rest.postForObject(url, payload, String.class);

    // Step 2: บันทึกลง DB ฝั่งลูกค้า
    Customer c = new Customer();
    c.setCustomerName(customerName);
    c.setCustomerPhone(customerPhone);
    c.setLocation(location);
    c.setAppointmentDates(date); // String format เช่น "2025-05-15T10:00"
    c.setTechnicianID(String.valueOf(techId)); // ต้องแปลงเป็น String
    customerRepo.save(c);

    return response;
}
}
