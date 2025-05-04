package dev.panachai.customer;



import org.springframework.web.client.RestTemplate;

public class TEST1Expertise {
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
    }
        
}
