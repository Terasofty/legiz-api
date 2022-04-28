package com.alessandro54.jwtspring.repository;

import com.alessandro54.jwtspring.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
