package com.spring.boot.api.model.service;

import com.spring.boot.api.model.dao.IClientDao;
import com.spring.boot.api.model.dao.IInvoiceDao;
import com.spring.boot.api.model.entity.Client;
import com.spring.boot.api.model.entity.Invoice;
import com.spring.boot.api.model.entity.Region;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class IClientServiceImpl implements IClientService {

    private final IClientDao clientDao;
    private final IInvoiceDao invoiceDao;


    public IClientServiceImpl(IClientDao clientDao, IInvoiceDao invoiceDao) {
        this.clientDao = clientDao;
        this.invoiceDao = invoiceDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Client> findAll() {
        return (List<Client>) clientDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Client> findAll(Pageable pageable) {
        return clientDao.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Client findById(Long id) {
        return clientDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Client save(Client client) {
        return clientDao.save(client);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        clientDao.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Region> findAllRegion() {
        return clientDao.findAllRegion();
    }

    @Override
    @Transactional(readOnly = true)
    public Invoice findInvoiceById(Long id) {
        return invoiceDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Invoice saveInvoice(Invoice invoice) {
        return invoiceDao.save(invoice);
    }

    @Override
    @Transactional
    public void deleteInvoiceById(Long id) {
        invoiceDao.deleteById(id);
    }
}
