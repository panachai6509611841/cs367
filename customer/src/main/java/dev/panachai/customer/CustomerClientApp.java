package dev.panachai.customer;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.client.RestTemplate;


public class CustomerClientApp {
    public static void main(String[] args) {
        RestTemplate rest = new RestTemplate();
        // === 1. ค้นหาช่างตามความถนัด ===
        String expertise = "Electrician";
        String searchUrl = "http://localhost:8081/search/" + expertise;

        try {
            String prettyResult = rest.getForObject(searchUrl, String.class);
            System.out.println("----------------------------------------");
            System.out.println(prettyResult);
            System.out.println("----------------------------------------\n");
        } catch (Exception e) {
            System.out.println("Can't Fine It : " + e.getMessage());
        }

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
