package com.spring.boot.api.model.service;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class UploadFileServiceImpl implements IUploadFileService {

    public static final String UPLOADS = "uploads";

    @Override
    public Resource load(String fileName) throws MalformedURLException {
        Path filePath = getPath(fileName);
        Resource resource = null;

        try {
            resource = new UrlResource(filePath.toUri());
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error during of creating of URI");
        }

        if (!resource.exists() && !resource.isReadable()) {
            filePath = getDefaultImagePath();
            resource = new UrlResource(filePath.toUri());
        }

        return resource;
    }

    @Override
    public String copy(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename() == null ? "" : file.getOriginalFilename();
        String fileName = UUID.randomUUID().toString() + "_" + originalFilename.replace(" ", "");
        Path filePath = getPath(fileName);
        Files.copy(file.getInputStream(), filePath);

        return fileName;
    }

    @Override
    public boolean delete(String fileName) {
        if (fileName != null && fileName.length() > 0) {
            Path pathPrevFile = getPath(fileName);
            File prevFile = pathPrevFile.toFile();
            if (prevFile.exists() && prevFile.canRead()) {
                prevFile.delete();
            }
        }

        return false;
    }

    private Path getPath(String fileFile) {
        return Paths.get(UPLOADS).resolve(fileFile).toAbsolutePath();
    }

    private Path getDefaultImagePath() {
        Path uploads = Paths.get("src/main/resources/static/images");
        return uploads.resolve("no_user.png").toAbsolutePath();
    }

}
