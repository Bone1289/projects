package com.spring.boot.api.controller;

import com.spring.boot.api.model.entity.Client;
import com.spring.boot.api.model.service.IClientService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.xml.crypto.Data;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
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
            Client client = clientService.findById(id);
            deleteClientFile(client);

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

    @PostMapping("/clients/uploads")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file, @RequestParam("id") Long id) {
        Map<String, Object> response = new HashMap<>();

        Client client = clientService.findById(id);

        if (!file.isEmpty()) {
            String originalFilename = file.getOriginalFilename() == null ? "" : file.getOriginalFilename();
            String fileName = UUID.randomUUID().toString() + "_" + originalFilename.replace(" ", "");
            Path filePath = getClientFilePath(fileName);

            try {
                Files.copy(file.getInputStream(), filePath);
            } catch (IOException ex) {
                response.put("message", "An error occurred during upload of the file.");
                if (ex.getMessage() != null) {
                    response.put("error", ex.toString());
                }
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            deleteClientFile(client);

            client.setPhoto(fileName);
            clientService.save(client);

            response.put("client", client);
            response.put("message", "File was upload successfully");
        }

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/uploads/img/{fileName:.+}")
    public ResponseEntity<Resource> viewPhoto(@PathVariable String fileName) {
        Path filePath = getClientFilePath(fileName);

        Resource resource;

        try {
            resource = new UrlResource(filePath.toUri());
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error during of creating of URI");
        }

        if (!resource.exists() && !resource.isReadable()) {
            filePath = getDefaultImagePath();
            try {
                resource = new UrlResource(filePath.toUri());
            } catch (MalformedURLException e) {
                throw new RuntimeException("Error during of creating of URI");
            }
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + resource.getFilename() + "\"");
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }

    private Path getClientFilePath(@PathVariable String fileName) {
        Path uploads = Paths.get("uploads");
        File uploadDirectory = uploads.toFile();
        if (!uploadDirectory.exists()) {
            uploadDirectory.mkdir();
        }
        return uploads.resolve(fileName).toAbsolutePath();
    }

    private Path getDefaultImagePath() {
        Path uploads = Paths.get("src/main/resources/static/images");
        return uploads.resolve("no_user.png").toAbsolutePath();
    }

    private void deleteClientFile(Client client) {
        String prevFileName = client.getPhoto();

        if (prevFileName != null && prevFileName.length() > 0) {
            Path pathPrevFile = getClientFilePath(prevFileName);
            File prevFile = pathPrevFile.toFile();
            if (prevFile.exists() && prevFile.canRead()) {
                prevFile.delete();
            }
        }
    }
}
