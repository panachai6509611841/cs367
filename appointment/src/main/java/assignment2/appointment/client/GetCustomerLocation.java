package assignment2.appointment.client;

import org.springframework.web.client.RestTemplate;

public class GetCustomerLocation {
    public static void main(String[] args) {
        RestTemplate rest = new RestTemplate();

        String customerName = "Betty";

        try {
            String locationUrl = "http://localhost:8080/technician-access/location?customerName=" + customerName;
            String location = rest.getForObject(locationUrl, String.class);

            System.out.println(" [Customer Location Information]");
            System.out.println("-------------------------------------------------");
            System.out.println("Customer Name : " + customerName);
            System.out.println("Location : " + location);
            System.out.println("-------------------------------------------------\n");

        } catch (Exception e) {
            System.out.println("Can't Find It : " + e.getMessage());
        }
    }
}
