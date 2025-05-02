package assignment2.appointment;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class TechnicianClientApp {
    public static void main(String[] args) {
        RestTemplate rest = new RestTemplate();

        String technicianId = "2";
        String customerName = "Betty";


        // === 1. ขอที่อยู่ของลูกค้า ===
        try {
            String locationUrl = "http://localhost:8080/technician-access/location?customerName=" + customerName;
            String location = rest.getForObject(locationUrl, String.class);

            System.out.println(" [Customer Location]");
            System.out.println("----------------------------------------");
            System.out.println(location);
            System.out.println("----------------------------------------\n");

        } catch (Exception e) {
            System.out.println("❌ Can't Find It : " + e.getMessage());
        }

        // === 2. ยกเลิกนัดลูกค้าและลบทั้งสองฝั่ง ===
        try {
            String cancelUrl = "http://localhost:8081/delete/cancel-booking-tech?technicianId=" + technicianId + "&customerName=" + customerName;
            ResponseEntity<String> response = rest.exchange(cancelUrl, org.springframework.http.HttpMethod.DELETE, null, String.class);

            System.out.println(" [Customer Appointment Cancellation Results]");
            System.out.println("----------------------------------------");
            System.out.println(response.getBody());
            System.out.println("----------------------------------------");

        } catch (Exception e) {
            System.out.println("❌ Delete failed : " + e.getMessage());
        }
    }
}
