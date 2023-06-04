package com.auto_parts_online_shop.service;

import com.auto_parts_online_shop.model.User;

public interface UserService {

    User register(User user);

    User getByEmail(String email);
}
