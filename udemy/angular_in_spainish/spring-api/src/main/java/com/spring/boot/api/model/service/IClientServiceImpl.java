package com.spring.boot.api.model.service;

import com.spring.boot.api.model.dao.IClientDao;
import com.spring.boot.api.model.entity.Client;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class IClientServiceImpl implements IClientService {

    private final IClientDao clientDao;

    public IClientServiceImpl(IClientDao clientDao) {
        this.clientDao = clientDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Client> findAll() {
        return (List<Client>) clientDao.findAll();
    }
}
