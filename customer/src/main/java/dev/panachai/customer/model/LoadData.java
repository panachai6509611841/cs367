package dev.panachai.customer.model;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dev.panachai.customer.repository.AppointmentRepository;

@Configuration
public class LoadData {

    @Bean
    CommandLineRunner initDatabase(AppointmentRepository appointmentRepository) {
        return args -> {
            // Mock ลูกค้า ID = 1, ช่าง ID = 10
            appointmentRepository.save(new Appointment(null, 1L, 10L, LocalDateTime.of(2025, 5, 15, 10, 0), "PENDING"));
            appointmentRepository.save(new Appointment(null, 1L, 11L, LocalDateTime.of(2025, 5, 16, 14, 0), "CONFIRMED"));

            // Mock ลูกค้า ID = 2, ช่าง ID = 10
            appointmentRepository.save(new Appointment(null, 2L, 10L, LocalDateTime.of(2025, 5, 17, 9, 30), "PENDING"));
        };
    }

}
