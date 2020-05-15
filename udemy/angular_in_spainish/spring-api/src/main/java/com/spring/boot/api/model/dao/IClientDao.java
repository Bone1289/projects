package com.spring.boot.api.model.dao;

import com.spring.boot.api.model.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface IClientDao extends JpaRepository<Client, Long> {
}
