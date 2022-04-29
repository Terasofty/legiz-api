package com.terasofty.legiz;

import com.terasofty.legiz.domain.Role;
import com.terasofty.legiz.service.UserService;
import com.terasofty.legiz.domain.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@SpringBootApplication
@EnableSwagger2
public class LegizApplication {

    public static void main(String[] args) {
        SpringApplication.run(LegizApplication.class, args);
    }

    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    CommandLineRunner run(UserService userService) {
        return args -> {
            userService.saveRole(new Role(null, "ROLE_USER"));
            userService.saveRole(new Role(null, "ROLE_ADMIN"));
            userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

            userService.saveUser(new User(null, "John", "john", "1234", new ArrayList<>()));
            userService.saveUser(new User(null, "Alessandro", "hyper", "12345", new ArrayList<>()));

            userService.addRoleToUser("hyper", "ROLE_ADMIN");
            userService.addRoleToUser("hyper", "ROLE_USER");
            userService.addRoleToUser("john", "ROLE_USER");
        };
    }

}
