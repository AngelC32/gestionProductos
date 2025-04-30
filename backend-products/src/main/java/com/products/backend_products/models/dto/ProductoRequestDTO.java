package com.products.backend_products.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProductoRequestDTO {

    @NotBlank
    private String nombre;
    @NotNull
    private Float precio;
    @NotNull
    private Long stock;
    @NotNull
    private Long categoriaId;
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Float getPrecio() {
        return precio;
    }
    public void setPrecio(Float precio) {
        this.precio = precio;
    }
    public Long getStock() {
        return stock;
    }
    public void setStock(Long stock) {
        this.stock = stock;
    }
    public Long getCategoriaId() {
        return categoriaId;
    }
    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }

    
}
