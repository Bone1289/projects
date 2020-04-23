package com.spring.boot.api.controller;

import com.spring.boot.api.model.entity.Client;
import com.spring.boot.api.model.service.IClientService;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/clients/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {
        Client client = null;
        Map<String, Object> response = new HashMap<>();

        try {
            client = clientService.findById(id);
        } catch (DataAccessException e) {
            response.put("message", "An error occurred during query of database.");
            if (e.getMessage() != null) {
                response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            }
        }

        if (client == null) {
            response.put("message", "Client ID: ".concat(id.toString().concat(" is not present in database")));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(client, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/clients")
    @ResponseStatus(HttpStatus.CREATED)
    public Client create(@RequestBody Client client) {
        return clientService.save(client);
    }

    @PutMapping("/clients/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Client update(@RequestBody Client client, @PathVariable Long id) {
        Client currentClient = clientService.findById(id);

        currentClient.setFirstName(client.getFirstName());
        currentClient.setLastName(client.getLastName());
        currentClient.setEmail(client.getEmail());

        return clientService.save(currentClient);
    }

    @DeleteMapping("/clients/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        clientService.delete(id);
    }
}
