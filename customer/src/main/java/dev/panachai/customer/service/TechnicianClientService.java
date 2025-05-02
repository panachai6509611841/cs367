package dev.panachai.customer.service;

import java.util.List;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TechnicianClientService {
    
    private final RestTemplate rest ;
    public TechnicianClientService(RestTemplate rest) {
        this.rest = rest;
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
public String bookAppointment(Long techId, String date, String customerName) {
    String url = "http://localhost:8081/" + techId + "/book";
    Map<String, String> payload = Map.of(
        "appointmentDate", date,
        "customerName", customerName
    );
    return rest.postForObject(url, payload, String.class);
}
}
