package com.oktenweb.medbookback.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public interface FileService {
    String storeFile(MultipartFile file) throws IOException;
}
