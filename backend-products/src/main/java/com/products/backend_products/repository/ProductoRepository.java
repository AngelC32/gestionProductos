package com.products.backend_products.repository;

import org.springframework.data.repository.CrudRepository;

import com.products.backend_products.models.Producto;

public interface ProductoRepository  extends CrudRepository<Producto,Long>{

}
