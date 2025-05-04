package assignment2.appointment;

import org.springframework.web.client.RestTemplate;

public class TEST1Location {
    public static void main(String[] args) {
        RestTemplate rest = new RestTemplate();
    
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
    }
}
