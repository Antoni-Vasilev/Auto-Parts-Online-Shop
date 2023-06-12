package com.auto_parts_online_shop.api.Auth;

import com.auto_parts_online_shop.controller.AuthenticationController;
import com.auto_parts_online_shop.dto.AuthenticationRequest;
import com.auto_parts_online_shop.model.User;
import com.auto_parts_online_shop.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AuthApiLoginTest {

    @Autowired
    private AuthenticationController authenticationController;

    @Autowired
    private UserService userService;

    @Test
    void login() {
        User user = User.builder()
                .username("Test")
                .email("test@gmail.com")
                .password("password")
                .build();
        User savedUser = authenticationController.register(user).getBody();

        AuthenticationRequest request = new AuthenticationRequest(savedUser.getEmail(), savedUser.getPassword());
        authenticationController.login(request);

        userService.delete(savedUser);
    }
}
