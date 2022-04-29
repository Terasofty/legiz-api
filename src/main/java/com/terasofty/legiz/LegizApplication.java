package com.terasofty.legiz;

import com.terasofty.legiz.api.domain.models.Role;
import com.terasofty.legiz.api.domain.service.UserService;
import com.terasofty.legiz.api.domain.models.User;
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
            userService.saveRole(new Role(null, "ROLE_CLIENT"));
            userService.saveRole(new Role(null, "ROLE_LAWYER"));
            userService.saveRole(new Role(null, "ROLE_ADMIN"));

            userService.saveUser(new User(null, "John", "john", "1234", new ArrayList<>()));
            userService.saveUser(new User(null, "Alessandro", "hyper", "12345", new ArrayList<>()));

            userService.addRoleToUser("hyper", "ROLE_ADMIN");
            userService.addRoleToUser("john", "ROLE_LAWYER");
        };
    }

}
