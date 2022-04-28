package com.alessandro54.jwtspring.repository;

import com.alessandro54.jwtspring.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
