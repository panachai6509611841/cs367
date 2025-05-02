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
      log.info("Loading "+ repository.save(new Technician( "1","Bob","0812546361", "Electrician", "9/10/2025" ,"Blue" )));
      log.info("Loading "+ repository.save(new Technician( "1","Bob","0812546361", "Electrician",  "10/5/2025", "Simon" )));
      log.info("Loading "+ repository.save(new Technician("2","Alice","0616521471", "Mechanic", null, null)));
      log.info("Loading "+ repository.save(new Technician("3", "Max","0812546361", "Electrician", "10/10/2025", "Pop" )));
      log.info("Loading "+ repository.save(new Technician( "3","Max","0812546361", "Electrician", "5/10/2025", "Laura" )));
      log.info("Loading "+ repository.save(new Technician("4","Milk","0616521471", "Mechanic", null, null)));
    };

  }
}
