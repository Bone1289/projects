package com.spring.boot.api.model.service;

import com.spring.boot.api.model.entity.Client;

import java.util.List;

public interface IClientService {

    public List<Client> findAll();
}
