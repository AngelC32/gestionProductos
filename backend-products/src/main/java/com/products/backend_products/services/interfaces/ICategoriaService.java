package com.products.backend_products.services.interfaces;

import java.util.List;
import com.products.backend_products.models.dto.CategoriaDTO;

public  interface ICategoriaService {

    CategoriaDTO create(CategoriaDTO categoria);
    CategoriaDTO update(Long id, CategoriaDTO categoria);
    List<CategoriaDTO> findAll();
    CategoriaDTO findById(Long id);
    CategoriaDTO delete(Long id);
}
