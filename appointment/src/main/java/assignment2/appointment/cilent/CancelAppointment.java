package assignment2.appointment.cilent;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class CancelAppointment {
     public static void main(String[] args) {
        RestTemplate rest = new RestTemplate();

        String technicianId = "2";
        String customerName = "Betty";

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
