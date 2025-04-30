package com.products.backend_products.exceptions;

public class NotImageFormatException extends RuntimeException{
    public NotImageFormatException(String message){
        super(message);
    }
}