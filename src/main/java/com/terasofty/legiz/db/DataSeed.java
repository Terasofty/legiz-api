package com.terasofty.legiz.db;

import com.terasofty.legiz.api.domain.models.Role;
import com.terasofty.legiz.api.domain.models.Specialization;
import com.terasofty.legiz.api.domain.models.User;
import com.terasofty.legiz.api.domain.service.SpecializationsService;
import com.terasofty.legiz.api.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class DataSeed implements CommandLineRunner {
    @Autowired
    UserService userService;
    @Autowired
    SpecializationsService specializationsService;

    @Override
    public void run(String... args) throws Exception {
        createRoles();
        createUsers();
        createSpecializations();
    }
    private void createRoles() {
        userService.saveRole(new Role(null, "ROLE_CLIENT"));
        userService.saveRole(new Role(null, "ROLE_LAWYER"));
        userService.saveRole(new Role(null, "ROLE_ADMIN"));
    }
    private void createUsers() {
        userService.createUser(new User(null, "John", "Smith", "john", "12345", new ArrayList<>()));
        userService.createUser(new User(null, "Tony", "Green", "tony", "12345", new ArrayList<>()));
        userService.createUser(new User(null, "Alessandro", "Chumpitaz", "hyper", "12345", new ArrayList<>()));

        userService.addRoleToUser("hyper", "ROLE_ADMIN");
        userService.addRoleToUser("john", "ROLE_LAWYER");
        userService.addRoleToUser("tony", "ROLE_LAWYER");
    }
    private void createSpecializations() {
        specializationsService.createSpecialization(new Specialization(null, "Criminal"));
        specializationsService.createSpecialization(new Specialization(null, "Business"));
        specializationsService.createSpecialization(new Specialization(null, "Family"));
        specializationsService.createSpecialization(new Specialization(null, "International"));
    }
    private void createLawyers() {

    }
}
