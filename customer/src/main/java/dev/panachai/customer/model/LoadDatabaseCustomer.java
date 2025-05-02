package dev.panachai.customer.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dev.panachai.customer.repository.CustomerRepository;

@Configuration
public class LoadDatabaseCustomer {
  public static final Logger log = LoggerFactory.getLogger(LoadDatabaseCustomer.class);

  @Bean
  CommandLineRunner setupData(CustomerRepository repository) {
    return args -> {
      log.info("Loading "+ repository.save(new Customer( "Simon","0812546361", "Electrician", "10/5/2025", "111" )));
      log.info("Loading "+ repository.save(new Customer( "Blue","0812546361", "Electrician", "10/5/2025", "111" )));
      log.info("Loading "+ repository.save(new Customer( "Pop","0812546361", "Electrician", "10/5/2025", "111" )));
      log.info("Loading "+ repository.save(new Customer( "Laura","0812546361", "Electrician", "10/5/2025", "111" )));
    };

  }
}
