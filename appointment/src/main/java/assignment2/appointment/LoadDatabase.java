package assignment2.appointment;

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
      log.info("Loading "+ repository.save(new Technician("Bob","0812546361", "Electrician", "10/5/2025",  "Susan" )));
      log.info("Loading "+ repository.save(new Technician("Alice","0616521471", "Mechanic", null, null)));
      log.info("Loading "+ repository.save(new Technician("Max","0812546361", "Electrician", null,  null )));
      log.info("Loading "+ repository.save(new Technician("Nick","0616521471", "Engine", null, null)));
    };

  }
}
