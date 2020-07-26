package com.spring.boot.api.model.dao;

import com.spring.boot.api.model.entity.Invoice;
import org.springframework.data.repository.CrudRepository;

public interface IInvoiceDao extends CrudRepository<Invoice, Long> {
}
