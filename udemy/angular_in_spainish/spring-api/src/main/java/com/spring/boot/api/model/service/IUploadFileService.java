package com.spring.boot.api.model.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;

public interface IUploadFileService {

    Resource load(String fileName) throws MalformedURLException;

    String copy(MultipartFile fileArchive) throws IOException;

    boolean delete(String fileName);
}
