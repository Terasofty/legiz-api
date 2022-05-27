package com.terasofty.legiz.db;

import com.terasofty.legiz.api.domain.models.*;
import com.terasofty.legiz.api.domain.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class DataSeed implements CommandLineRunner {
    @Autowired
    UserService userService;
    @Autowired
    SpecializationsService specializationsService;
    @Autowired
    SubscriptionService subscriptionService;
    @Autowired
    LawyersService lawyersService;
    @Autowired
    ClientsService clientsService;

    @Override
    public void run(String... args) throws Exception {
        if (!Objects.equals(System.getenv("SPRING_ENV"), "prod")) {
            createRoles();
            createUsers();
            createSpecializations();
            createSubscriptions();
            createLawyers();
            createClient();
        }
    }
    private void createRoles() {
        userService.saveRole(new Role(null, "ROLE_CUSTOMER"));
        userService.saveRole(new Role(null, "ROLE_LAWYER"));
        userService.saveRole(new Role(null, "ROLE_ADMIN"));
    }
    private void createUsers() {
        userService.createUser(new User(null, "John", "Smith", "john", "12345", new ArrayList<>()));
        userService.createUser(new User(null, "Tony", "Green", "tony", "12345", new ArrayList<>()));
        userService.createUser(new User(null, "Nick", "Green", "nick", "12345", new ArrayList<>()));
        userService.createUser(new User(null, "Hans", "Green", "hans", "12345", new ArrayList<>()));

        userService.createUser(new User(null, "Alessandro", "Chumpitaz", "hyper", "12345", new ArrayList<>()));

        userService.addRoleToUser("hyper", "ROLE_ADMIN");
        userService.addRoleToUser("hyper", "ROLE_CUSTOMER");

        userService.addRoleToUser("john", "ROLE_LAWYER");
        userService.addRoleToUser("tony", "ROLE_LAWYER");
        userService.addRoleToUser("hans", "ROLE_LAWYER");
        userService.addRoleToUser("nick", "ROLE_LAWYER");
    }
    private void createSpecializations() {
        specializationsService.createSpecialization(new Specialization(null, "Criminal"));
        specializationsService.createSpecialization(new Specialization(null, "Business"));
        specializationsService.createSpecialization(new Specialization(null, "Family"));
        specializationsService.createSpecialization(new Specialization(null, "International"));
    }
    private void createSubscriptions() {
        subscriptionService.createSubscription(new Subscription(null, "MEMBER", 39.99, ""));
    }
    private void createLawyers() {
        List<String> lawyers = List.of(new String[]{"john", "tony", "hans", "nick"});
        lawyers.forEach(lawyer -> {
            lawyersService.createLawyer(
                    new Lawyer(
                            null,
                            userService.getUser(lawyer),
                            new ArrayList<>(),
                            new Subscription()
                    ));
            subscriptionService.addSubscriptionToLawyer(lawyer, "MEMBER");
            specializationsService.addSpecializationToLawyer(lawyer, "Criminal");
            specializationsService.addSpecializationToLawyer(lawyer, "Family");
        });
    }
    private void createClient() {
        clientsService.createClient(new Client(null, userService.getUser("hyper")));
    }
}
