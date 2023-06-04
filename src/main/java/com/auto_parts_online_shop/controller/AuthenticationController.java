package com.auto_parts_online_shop.controller;

import com.auto_parts_online_shop.config.JwtUtils;
import com.auto_parts_online_shop.dao.UserDao;
import com.auto_parts_online_shop.dto.AuthenticationRequest;
import com.auto_parts_online_shop.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    private final UserDao userDao;
    private final JwtUtils jwtUtils;
    private final UserService userService;

    @Autowired
    public AuthenticationController(UserDao userDao, JwtUtils jwtUtils, UserService userService) {
        this.userDao = userDao;
        this.jwtUtils = jwtUtils;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthenticationRequest request) {
        final UserDetails user = userDao.findUserByEmail(request.getEmail());
        if (user != null) {
            return ResponseEntity.ok(jwtUtils.generateToken(user));
        }

        return ResponseEntity.status(400).body("Some error has occurred");
    }

    @PostMapping("/register")
    public ResponseEntity<com.auto_parts_online_shop.model.User> register(@RequestBody @Valid com.auto_parts_online_shop.model.User user) {
        return ResponseEntity.ok(userService.register(user));
    }
}
