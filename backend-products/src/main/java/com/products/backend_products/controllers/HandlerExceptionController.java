package com.products.backend_products.controllers;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.products.backend_products.exceptions.DataNotFoundException;
import com.products.backend_products.exceptions.ImagenNotFoundException;
import com.products.backend_products.exceptions.NotImageFormatException;
import com.products.backend_products.models.Error;

@RestControllerAdvice
public class HandlerExceptionController {
    @ExceptionHandler({NoHandlerFoundException.class, NoResourceFoundException.class})
    public ResponseEntity<Error> notFoundEx(Exception e) { 
        Error error = new Error();
        error.setDate(new Date());
        error.setError("Api rest no encontrado");
        error.setMessage(e.getMessage());

        error.setStatus(HttpStatus.NOT_FOUND.value());

        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(error);
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<Error> dataNotFoundException(Exception ex){
        Error error= new Error();
        error.setDate(new Date());
        error.setError("No se encontró registro en la BD para la consulta realizada");
        error.setMessage(ex.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.value());

        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(error);
    }

    @ExceptionHandler(NotImageFormatException.class)
    public ResponseEntity<Error> uploadFileException(Exception ex){
        Error error= new Error();
        error.setDate(new Date());
        error.setError("Ha ocurrido un error con el archivo");
        error.setMessage(ex.getMessage());
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(error);
    }

    @ExceptionHandler(ImagenNotFoundException.class)
    public ResponseEntity<Error> ImageNotFoundException (Exception ex){
        Error error= new Error();
        error.setDate(new Date());
        error.setError("Ocurrió un error en la eliminación del archivo");
        error.setMessage(ex.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.value());

        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(error);
    }

}
