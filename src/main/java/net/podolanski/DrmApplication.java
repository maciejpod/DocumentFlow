package net.podolanski;

import net.podolanski.dao.repository.RequestRepository;
import net.podolanski.dao.repository.UserRepository;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DrmApplication {

        private static final Logger log = LoggerFactory.getLogger(DrmApplication.class);
    
	public static void main(String[] args) {
		SpringApplication.run(DrmApplication.class, args);
	}
        
        
        
        @Bean
        CommandLineRunner runner(
                    UserRepository ur,
                    RequestRepository rr
                    
                                        ) {
            return (arg) -> {
               //rr.findToProced(ur.findOne(3)).forEach((n)->log.info(n.getRequestId() + ""));
            };
        }
}
