package com.auto_parts_online_shop.service.impl;

import com.auto_parts_online_shop.model.User;
import com.auto_parts_online_shop.repository.UserRepository;
import com.auto_parts_online_shop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User register(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
