package dev.panachai.customer;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class TestMyCustomerAPI {
    public static void main(String[] args) {
        RestTemplate rest = new RestTemplate();

        // เรียก API my-appointments ของตัวเอง
        String url = "http://localhost:8080/customer/my-appointments?customerId=1";

        try {
            ResponseEntity<Object[]> response = rest.getForEntity(url, Object[].class);
            System.out.println("✅ นัดของลูกค้า ID 1:");
            for (Object obj : response.getBody()) {
                System.out.println(obj);
            }
        } catch (Exception e) {
            System.err.println("❌ เรียกไม่สำเร็จ: " + e.getMessage());
        }
    }
}
