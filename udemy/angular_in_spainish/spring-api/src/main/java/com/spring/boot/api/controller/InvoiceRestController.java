package com.spring.boot.api.controller;

import com.spring.boot.api.model.entity.Invoice;
import com.spring.boot.api.model.service.IClientService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://locahost:4200"})
@RestController
@RequestMapping("/api")
public class InvoiceRestController {

    private final IClientService clientService;

    public InvoiceRestController(IClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/invoices/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Invoice show(@PathVariable Long id) {
        return clientService.findInvoiceById(id);
    }

    @DeleteMapping("/invoices/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        clientService.deleteInvoiceById(id);
    }
}
