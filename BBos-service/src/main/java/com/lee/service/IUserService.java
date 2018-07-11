package com.lee.service;

import com.lee.domain.User;

public interface IUserService {
    User login(User model);

    void editPassword(String id, String password);
}
