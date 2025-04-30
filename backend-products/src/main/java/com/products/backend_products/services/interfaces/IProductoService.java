package com.products.backend_products.services.interfaces;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.products.backend_products.models.dto.ProductoRequestDTO;
import com.products.backend_products.models.dto.ProductoResponseDTO;

public interface IProductoService {
    ProductoResponseDTO create(ProductoRequestDTO producto);
    ProductoResponseDTO update(Long id,ProductoRequestDTO producto);
    List<ProductoResponseDTO> findAll();
    ProductoResponseDTO findById(Long id);
    ProductoResponseDTO delete(Long id) throws IOException;
    ProductoResponseDTO guardarImagen(Long id, MultipartFile foto) throws IOException;
    ProductoResponseDTO eliminarImagen(Long id) throws IOException;
    byte[] generatePdfContent();
}
