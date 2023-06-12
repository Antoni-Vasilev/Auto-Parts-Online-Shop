package com.auto_parts_online_shop.api.Auth;

import com.auto_parts_online_shop.controller.AuthenticationController;
import com.auto_parts_online_shop.model.User;
import com.auto_parts_online_shop.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AuthApiRegisterTest {

    @Autowired
    private AuthenticationController authenticationController;

    @Autowired
    private UserService userService;

    @Test
    void register() {

        User user = User.builder()
                .username("Test")
                .email("test@gmail.com")
                .password("password")
                .build();
        User savedUser = authenticationController.register(user).getBody();

        userService.delete(savedUser);
    }
}
