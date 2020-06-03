package com.spring.boot.api.model.dao;

import com.spring.boot.api.model.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface IUserDao extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
