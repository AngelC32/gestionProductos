package com.products.backend_products.models.dto;

import java.time.LocalDateTime;

import com.products.backend_products.models.Categoria;

public class ProductoResponseDTO {
    private Long id;
    private String nombre;
    private Float precio;
    private Long stock;
    private String foto;
    private Categoria categoria;
    private LocalDateTime fecCreacion;
    private LocalDateTime fecActualizacion;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
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
    public String getFoto() {
        return foto;
    }
    public void setFoto(String foto) {
        this.foto = foto;
    }
    public Categoria getCategoria() {
        return categoria;
    }
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    public LocalDateTime getFecCreacion() {
        return fecCreacion;
    }
    public void setFecCreacion(LocalDateTime fecCreacion) {
        this.fecCreacion = fecCreacion;
    }
    public LocalDateTime getFecActualizacion() {
        return fecActualizacion;
    }
    public void setFecActualizacion(LocalDateTime fecActualizacion) {
        this.fecActualizacion = fecActualizacion;
    }    

    
}
