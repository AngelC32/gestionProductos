package com.products.backend_products.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.products.backend_products.models.dto.CategoriaDTO;
import com.products.backend_products.services.CategoriaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categoria")
public class CategoriaController extends BaseController {
    @Autowired
    private CategoriaService service;

    @GetMapping
    public List<CategoriaDTO> findAll(){
        return service.findAll();
    }
    @GetMapping("/{id}")
    public CategoriaDTO findBydId(@PathVariable Long id){
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CategoriaDTO categoriaDTO, BindingResult result){
        if(result.hasFieldErrors()){
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(categoriaDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody CategoriaDTO categoriaDTO, BindingResult result, @PathVariable Long id){
        if(result.hasFieldErrors()){
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.update(id, categoriaDTO));
    }
    @DeleteMapping("/{id}")
    public CategoriaDTO delete(@PathVariable Long id) {
        return service.delete(id);
    }

}
