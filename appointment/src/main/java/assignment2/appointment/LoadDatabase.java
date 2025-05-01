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
      log.info("Loading "+ repository.save(new Technician()));
    };
    
  }
}
