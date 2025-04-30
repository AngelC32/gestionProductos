package com.products.backend_products.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name="producto")
public class Producto {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_producto")
    private Long id;
    private String nombre;
    private Float precio;
    private Long stock;
    private String foto;
    @ManyToOne
    @JoinColumn(name="id_categoria")
    private Categoria categoria;

    @Column(name="fec_creacion")
    private LocalDateTime fecCreacion;

    @Column(name="fec_actualizacion")
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



    @PrePersist
    public void prePersist(){
        this.fecCreacion=LocalDateTime.now();
    }
}
