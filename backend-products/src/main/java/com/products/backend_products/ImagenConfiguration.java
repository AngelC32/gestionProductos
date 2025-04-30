package com.products.backend_products;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ImagenConfiguration implements WebMvcConfigurer{
    @Value("${upload.images.location}")
    private String uploadLocation;

    @Value("${upload.images.handler}")
    private String uploadPathHandler;

    @Override
    public void addResourceHandlers(@NonNull ResourceHandlerRegistry registry) {
        
        WebMvcConfigurer.super.addResourceHandlers(registry);
        registry.addResourceHandler(uploadPathHandler+"**") //Direccion para servir la imagen, ejemplo: localhost/imagen/asdas.jpg
                .addResourceLocations("file:"+uploadLocation); //Direccion fisica ejemplo: "file:C:\Imagenes\"
    }
}

