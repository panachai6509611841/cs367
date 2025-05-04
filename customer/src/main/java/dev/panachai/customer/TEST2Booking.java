package dev.panachai.customer;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.client.RestTemplate;

public class TEST2Booking {
    public static void main(String[] args) {
        RestTemplate rest = new RestTemplate();


        // 🔹 2. จองนัดกับช่าง ID = 2
        String bookingUrl = "http://localhost:8080/customer/book-and-track/2";
        Map<String, String> bookingPayload = new HashMap<>();
        bookingPayload.put("appointmentDate", "20/03/2025");
        bookingPayload.put("customerName", "Betty");
        bookingPayload.put("location", "Condo Plum 465/89");
        bookingPayload.put("customerPhone", "0972856574");

        try {
            String bookingResult = rest.postForObject(bookingUrl, bookingPayload, String.class);
            System.out.println("[Appointment booking results]");
            System.out.println("----------------------------------------");
            System.out.println(bookingResult);
            System.out.println("----------------------------------------\n");
        } catch (Exception e) {
            System.out.println("Can't make a Appointment : " + e.getMessage());
        }
}
}
