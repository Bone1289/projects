package com.spring.boot.api.model.service;

import com.spring.boot.api.model.entity.Client;
import com.spring.boot.api.model.entity.Invoice;
import com.spring.boot.api.model.entity.Region;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IClientService {

    List<Client> findAll();

    Page<Client> findAll(Pageable pageable);

    Client findById(Long id);

    Client save(Client client);

    void delete(Long id);

    List<Region> findAllRegion();

    Invoice findInvoiceById(Long id);

    Invoice saveInvoice(Invoice invoice);

    void deleteInvoiceById(Long id);
}
