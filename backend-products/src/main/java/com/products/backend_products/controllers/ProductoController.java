package com.products.backend_products.controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.products.backend_products.models.dto.ProductoRequestDTO;
import com.products.backend_products.models.dto.ProductoResponseDTO;
import com.products.backend_products.services.ProductoService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/producto")
public class ProductoController extends BaseController {
    @Autowired
    private ProductoService service;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody ProductoRequestDTO productoDTO, BindingResult result){
        if(result.hasFieldErrors()){
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(productoDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> create(@Valid @RequestBody ProductoRequestDTO productoDTO, BindingResult result, @PathVariable Long id){
        if(result.hasFieldErrors()){
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.update(id,productoDTO));
    }

    @GetMapping
    public List<ProductoResponseDTO> findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ProductoResponseDTO findById(@PathVariable Long id){
        return service.findById(id); 
    }

    @DeleteMapping("/{id}")
    public ProductoResponseDTO delete(@PathVariable Long id) throws IOException{
        return service.delete(id);
    }

    @PutMapping("/actualizarImagen/{id}")
    public ResponseEntity<?>actualizarImagen(@PathVariable Long id,
                                   @RequestPart("foto") MultipartFile foto) throws IOException {

        ProductoResponseDTO producto= service.guardarImagen(id,foto);
        return ResponseEntity.status(HttpStatus.CREATED).body(producto);
    }

    @DeleteMapping("/eliminarImagen/{id}")
    public ProductoResponseDTO eliminarImagen (@PathVariable Long id) throws IOException{
        return service.eliminarImagen(id);
    }

    @GetMapping("/pdf")
    public void downloadFile(HttpServletResponse response) throws IOException {
        try {
            byte[] pdfReport = service.generatePdfContent();
    
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=\"reporte.pdf\"");
            response.setContentLength(pdfReport.length);
    
            ByteArrayInputStream inStream = new ByteArrayInputStream(pdfReport);
            FileCopyUtils.copy(inStream, response.getOutputStream());
            response.flushBuffer(); 
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al generar PDF");
        }
    }


}
