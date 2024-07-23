package com.ichurch.backend.service;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;


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
    public Map<String,String> getEncodedImage(String imageName, int targetWidth, int targetHeight) throws IOException {

        Path imagePath = Paths.get(uploadDir, imageName);
        BufferedImage originalImage = ImageIO.read(Files.newInputStream(imagePath));

        BufferedImage resizedImage = resizeImage(originalImage, targetWidth, targetHeight);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(resizedImage, "jpg", baos);
        byte[] imageBytes = baos.toByteArray();

        String base64Image = Base64.getEncoder().encodeToString(imageBytes);
        String mimeType = "image/jpg";

        Map<String, String> imageMap = new HashMap<>();
        imageMap.put("image", base64Image);
        imageMap.put("mimeType", mimeType);

        return imageMap;
    }

    private BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);
        BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(resultingImage, 0, 0, null);
        g2d.dispose();
        return outputImage;
    }
}
