package com.products.backend_products.models.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;

public class CategoriaDTO {
    private Long id;
    @NotBlank
    private String nombre;
    @NotBlank
    private String descripcion;
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
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
