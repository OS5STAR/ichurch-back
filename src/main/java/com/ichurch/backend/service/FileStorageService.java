package com.ichurch.backend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;


@Service
public class FileStorageService {

    @Value("${file.manager.upload.dir}")
    private String uploadDir;

    public String storeBase64Image(String base64Image) throws IOException {
        byte[] decodedBytes = Base64.getDecoder().decode(base64Image);

        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs(); // Cria diretório se não existir
        }

        String fileName = System.currentTimeMillis() + ".jpg"; // Nome do arquivo
        String filePath = uploadDir + File.separator + fileName;

        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(decodedBytes); // Escreve os bytes decodificados no arquivo
        }

        return fileName; // Retorna o nome do arquivo salvo
    }
}
