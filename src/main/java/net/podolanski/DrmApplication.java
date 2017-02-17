package net.podolanski;

import net.podolanski.dao.User;
import net.podolanski.dao.repository.DepartmentRepository;
import net.podolanski.dao.repository.RequestRepository;
import net.podolanski.dao.repository.UserRepository;
import net.podolanski.service.UserRoleService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;

@SpringBootApplication
public class DrmApplication {

        private static final Logger log = LoggerFactory.getLogger(DrmApplication.class);
    
	public static void main(String[] args) {
		SpringApplication.run(DrmApplication.class, args);
	}

        @Bean
        CommandLineRunner runner(
                    PasswordEncoder pe,
                    UserRepository ur,
                    DepartmentRepository departmentRepository,
                    UserRoleService userRoleService
                    
                    
                                        ) {
            return (arg) -> {
                User user = ur.findOne(2);
                userRoleService.getDepartmentRoles(user).forEach((k,v) -> {
                    log.info(k.getName());
                    log.info(StringUtils.collectionToDelimitedString(v, " - "));
                });
            };
        }
}
