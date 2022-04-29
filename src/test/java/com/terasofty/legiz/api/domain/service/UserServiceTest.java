package com.terasofty.legiz.api.domain.service;

import com.terasofty.legiz.api.domain.models.User;
import com.terasofty.legiz.api.domain.persistence.RoleRepository;
import com.terasofty.legiz.api.domain.persistence.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;
    private User user;
    @InjectMocks
    private UserServiceImpl userService;
    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setId(1L);
        user.setName("Alessandro");
        user.setUsername("hyper");

    }
    @Test
    void getUsers() {
        System.out.println(userService.getUsers());
        when(userRepository.findAll()).thenReturn(List.of(user));
        assertNotNull(userService.getUsers());
    }
}