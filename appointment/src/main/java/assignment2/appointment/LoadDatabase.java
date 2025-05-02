package assignment2.appointment;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
  public static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner setupData(AppointmentRepository repository) {
    return args -> {
      log.info("Loading "+ repository.save(new Technician( "Bob","0812546361", "Electrician", List.of("10/5/2025"), List.of("Susan") )));
      log.info("Loading "+ repository.save(new Technician("Alice","0616521471", "Mechanic", new ArrayList<>(), new ArrayList<>())));
      log.info("Loading "+ repository.save(new Technician("Max","0812546361", "Electrician", new ArrayList<>(), new ArrayList<>() )));
      log.info("Loading "+ repository.save(new Technician("Nick","0616521471", "Engine", new ArrayList<>(), new ArrayList<>())));
    };

  }
}
