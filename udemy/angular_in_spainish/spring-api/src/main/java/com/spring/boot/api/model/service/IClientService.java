package com.spring.boot.api.model.service;

import com.spring.boot.api.model.entity.Client;

import java.util.List;

public interface IClientService {

    List<Client> findAll();

    Client findById(Long id);

    Client save(Client client);

    void delete(Long id);
}
