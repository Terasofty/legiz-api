package com.terasofty.legiz.db;

import com.terasofty.legiz.api.domain.models.*;
import com.terasofty.legiz.api.domain.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

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
        userService.createUser(new User(null, "Mayra", "Red", "mayra", "12345", new ArrayList<>()));
        userService.createUser(new User(null, "Alex", "Yellow", "alex", "12345", new ArrayList<>()));
        userService.createUser(new User(null, "Nick", "Green", "nick", "12345", new ArrayList<>()));
        userService.createUser(new User(null, "Hans", "Green", "hans", "12345", new ArrayList<>()));

        userService.createUser(new User(null, "Alessandro", "Chumpitaz", "hyper", "12345", new ArrayList<>()));

        userService.addRoleToUser("hyper", "ROLE_ADMIN");
        userService.addRoleToUser("hyper", "ROLE_CUSTOMER");

        userService.addRoleToUser("john", "ROLE_LAWYER");
        userService.addRoleToUser("tony", "ROLE_LAWYER");
        userService.addRoleToUser("mayra", "ROLE_LAWYER");
        userService.addRoleToUser("alex", "ROLE_LAWYER");
        userService.addRoleToUser("hans", "ROLE_LAWYER");
        userService.addRoleToUser("nick", "ROLE_LAWYER");

    }
    private void createSpecializations() {
        specializationsService.createSpecialization(new Specialization(null, "Criminal"));
        specializationsService.createSpecialization(new Specialization(null, "Business"));
        specializationsService.createSpecialization(new Specialization(null, "Family"));
        specializationsService.createSpecialization(new Specialization(null, "International"));
        specializationsService.createSpecialization(new Specialization(null, "Other"));
    }
    private void createSubscriptions() {
        subscriptionService.createSubscription(new Subscription(null, "MEMBER", 39.99, ""));
    }
    private void createLawyers() {
        String lorem = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque vitae rhoncus libero, non laoreet " +
                " Aenean ultrices nulla id dui varius, eget varius justo porta. Mauris dictum ex at ligula posuere, nec volutpat leo pretium." +
                " Duis ultrices aliquam sapien ac mattis. Pellentesque volutpat lacinia augue, interdum blandit orci pharetra et.";
        List<String> photos = List.of(new String[] {
                "https://www.vlaw.com/wp-content/uploads/2020/01/Can-a-Lawyer-from-Another-State-Represent-You.jpg",
                "https://ichef.bbci.co.uk/news/676/cpsprodpb/1604B/production/_118478109_gettyimages-1221070430.jpg",
                "https://media.istockphoto.com/photos/successful-business-woman-or-lawyer-picture-id514236016?k=20&m=514236016&s=612x612&w=0&h=gmoRJGZ-1CbFN8y5X-gJ4Zc5w73_92g6foV21kepocg=",
                "https://thumbs.dreamstime.com/b/female-lawyer-office-portrait-boutique-law-firm-signing-documents-54969196.jpg",
                "https://ehlinelaw.com/img/uploads/Ehline-G-Plus-Profile.jpg",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR7nbnm6vhyykZvcXxj43-Ou-WPL6r5Z-xmDw&usqp=CAU"
        });
        List<String> lawyers = List.of(new String[]{"john", "tony", "mayra", "alex", "hans", "nick"});

        AtomicReference<Integer> counter = new AtomicReference<>(0);
        lawyers.forEach(lawyer -> {
            lawyersService.createLawyer(
                    new Lawyer(null,
                            photos.get(counter.get()),
                            lorem,
                            userService.getUser(lawyer), new ArrayList<>(), new Subscription()
                    ));
            subscriptionService.addSubscriptionToLawyer(lawyer, "MEMBER");
            specializationsService.addSpecializationToLawyer(lawyer, "Criminal");
            specializationsService.addSpecializationToLawyer(lawyer, "Family");
            counter.getAndSet(counter.get() + 1);
        });
    }
    private void createClient() {
        clientsService.createClient(new Client(null, userService.getUser("hyper")));
    }
}
