package com.spring.boot.api.model.service;

import com.spring.boot.api.model.entity.User;

public interface IUserService {
    public User findByUsername(String username);
}
