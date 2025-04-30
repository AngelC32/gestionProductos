package com.products.backend_products.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class UploadService {

    public boolean isImageFile(MultipartFile file) {
        String contentType = file.getContentType();
        return contentType != null && contentType.startsWith("image/");
    }

    //validar que el contenido no sea un archivo disfrazado de imagen
    public boolean isImageContent(MultipartFile file) {
        try {
            BufferedImage image = ImageIO.read(file.getInputStream());
            return image != null;
        } catch (IOException e) {
            return false;
        }
    }

    public boolean deleteImage(String uploadLocation, String nombreFoto) throws IOException{
        Path file = Paths.get(uploadLocation + nombreFoto);
        if(Files.exists(file) && nombreFoto!=null && !nombreFoto.isBlank()){
            Files.deleteIfExists(file);
            return true;
        }
        //Devuelve false si no existe la imagen con el nombre indicado
        return false;
        
    }

    public boolean isValidImage(MultipartFile file) {
        return isImageFile(file) && isImageContent(file);
    }

    public boolean convertToJpg(String uploadLocation, String nombreFoto, MultipartFile foto) throws IOException{
        Path filePath = Paths.get(uploadLocation + nombreFoto);

        //convertir imagen a formato jpg
        BufferedImage image = ImageIO.read(foto.getInputStream());
        return ImageIO.write(image, "jpg", filePath.toFile());
    }

    public void subirImagen(String uploadLocation, String nombreFoto, MultipartFile foto) throws IOException{
        Path filePath = Paths.get(uploadLocation + nombreFoto);
        Files.createDirectories(Paths.get(uploadLocation));
        Files.copy(foto.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
    }
}
