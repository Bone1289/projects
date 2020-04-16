package com.spring.boot.api.model.dao;

import com.spring.boot.api.model.entity.Client;
import org.springframework.data.repository.CrudRepository;

public interface IClientDao extends CrudRepository<Client, Long> {
}
