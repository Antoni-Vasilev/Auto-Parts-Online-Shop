package com.auto_parts_online_shop.dao;

import com.auto_parts_online_shop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Collections;

@Repository
@RequiredArgsConstructor
public class UserDao {

    public final UserService userService;

    public UserDetails findUserByEmail(String email) {
        com.auto_parts_online_shop.model.User findUser = userService.getByEmail(email);
        UserDetails userDetails = new User(
                findUser.getEmail(),
                findUser.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority("ROLE_CLIENT"))
        );

        return userDetails;
    }
}
