package assignment2.appointment;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TechnicianClientService {
    private final RestTemplate rest;
        
    public TechnicianClientService(RestTemplate rest) {
        this.rest = rest;
    }
    public String getCustomerLocation(String customerName) {
        String url = "http://localhost:8080/technician-access/location?customerName=" + customerName;
        return rest.getForObject(url, String.class);
    }

    public String cancelCustomerBooking(String technicianId, String customerName) {
        String url = "http://localhost:8080/technician-access/cancel-booking?technicianId=" + technicianId + "&customerName=" + customerName;
        ResponseEntity<String> response = rest.exchange(url, HttpMethod.DELETE, null, String.class);
        return response.getBody();
    }
}
