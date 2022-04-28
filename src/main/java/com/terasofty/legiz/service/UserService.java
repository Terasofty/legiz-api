package com.terasofty.legiz.service;

import com.terasofty.legiz.domain.Role;
import com.terasofty.legiz.domain.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    List<User> getUsers();
}
