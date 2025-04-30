package com.products.backend_products.controllers;

import java.util.Map;
import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin({"http://localhost:4200","http://localhost:5173", "http://localhost:8081"})
public class BaseController {
    protected ResponseEntity<?> validation(BindingResult result){
        Map<String, String> errors= new HashMap<>();
        result.getFieldErrors().forEach(err-> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}
