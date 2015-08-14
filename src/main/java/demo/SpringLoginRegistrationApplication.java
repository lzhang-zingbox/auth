package demo;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan({"demo", "org.baeldung.persistence.dao", "org.baeldung.persistence.model", 
	"org.baeldung.persistence.service", "org.baeldung.registration", "org.baeldung.validation", 
	"org.baeldung.web.controller"})

@EnableMongoRepositories(basePackages = {"org.baeldung.persistence.dao"})

public class SpringLoginRegistrationApplication {
	
	static Logger log = Logger.getLogger(SpringLoginRegistrationApplication.class.getName());
	
    public static void main(String[] args) {
        SpringApplication.run(SpringLoginRegistrationApplication.class, args);
    }
}
