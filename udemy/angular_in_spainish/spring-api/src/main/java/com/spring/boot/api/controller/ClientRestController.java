package com.spring.boot.api.controller;

import com.spring.boot.api.model.entity.Client;
import com.spring.boot.api.model.service.IClientService;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.crypto.Data;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class ClientRestController {

    private final IClientService clientService;

    public ClientRestController(IClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/clients")
    public List<Client> index() {
        return clientService.findAll();
    }

    @GetMapping("/clients/page/{page}")
    public Page<Client> index(@PathVariable Integer page) {
        return clientService.findAll(PageRequest.of(page, 4));
    }

    @GetMapping("/clients/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {
        Client client;
        Map<String, Object> response = new HashMap<>();

        try {
            client = clientService.findById(id);
        } catch (DataAccessException e) {
            response.put("message", "An error occurred during query of database.");
            if (e.getMessage() != null) {
                response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            }
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (client == null) {
            response.put("message", "Client ID: ".concat(id.toString().concat(" is not present in database")));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @PostMapping("/clients")
    public ResponseEntity<?> create(@Valid @RequestBody Client client, BindingResult result) {
        Client savedClient;
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> MessageFormat.format("Field {0} contains following error {1}.", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            savedClient = clientService.save(client);
        } catch (DataAccessException e) {
            response.put("message", "An error occurred during insert in database.");
            if (e.getMessage() != null) {
                response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            }
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("client", savedClient);
        response.put("message", "Client created");

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/clients/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Client client, BindingResult result, @PathVariable Long id) {

        Client currentClient;
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> MessageFormat.format("Field {0} contains following error {1}.", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            currentClient = clientService.findById(id);
        } catch (DataAccessException e) {
            response.put("message", "An error occurred during query of database.");
            if (e.getMessage() != null) {
                response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            }
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (currentClient == null) {
            response.put("message", "Client ID: ".concat(id.toString().concat(" is not present in database")));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        try {
            currentClient.setFirstName(client.getFirstName());
            currentClient.setLastName(client.getLastName());
            currentClient.setEmail(client.getEmail());
            currentClient.setCreateAt(client.getCreateAt());
            clientService.save(currentClient);
        } catch (DataAccessException ex) {
            response.put("message", "An error occurred during update of database.");
            if (ex.getMessage() != null) {
                response.put("error", ex.getMessage().concat(": ").concat(ex.getMostSpecificCause().getMessage()));
            }
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("message", "Client updated");
        response.put("client", currentClient);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/clients/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();

        try {
            clientService.delete(id);
        } catch (DataAccessException e) {
            response.put("message", "An error occurred during insert in database.");
            if (e.getMessage() != null) {
                response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            }
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("message", "Client was deleted");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
