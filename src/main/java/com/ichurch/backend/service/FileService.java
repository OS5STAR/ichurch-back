package com.ichurch.backend.service;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;


@Service
public class FileService {

    @Value("${file.manager.upload.dir}")
    private String uploadDir;

    public String storeBase64Image(String base64Image, String idToLocate) throws IOException {
        byte[] decodedBytes = Base64.getDecoder().decode(base64Image);

        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs(); // Cria diretório se não existir
        }

        String fileName = idToLocate + ".jpg"; // Nome do arquivo
        String filePath = uploadDir + File.separator + fileName;

        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(decodedBytes); // Escreve os bytes decodificados no arquivo
        }

        return fileName; // Retorna o nome do arquivo salvo
    }
    @SneakyThrows()
    public byte[] getBase64Image(String imageName) throws IOException {
        Path imagePath = Paths.get(uploadDir, imageName);
        return Files.readAllBytes(imagePath);

    }
}
