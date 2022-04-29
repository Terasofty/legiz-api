package com.terasofty.legiz.api.domain.service;

import com.terasofty.legiz.api.domain.models.Role;
import com.terasofty.legiz.api.domain.models.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    List<User> getUsers();
}
