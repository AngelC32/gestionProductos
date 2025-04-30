package com.products.backend_products.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.products.backend_products.exceptions.DataNotFoundException;
import com.products.backend_products.models.Categoria;
import com.products.backend_products.models.dto.CategoriaDTO;
import com.products.backend_products.repository.CategoriaRepository;
import com.products.backend_products.services.interfaces.ICategoriaService;

@Service
public class CategoriaService implements ICategoriaService {

    @Autowired
    private CategoriaRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    @Override
    public CategoriaDTO create(CategoriaDTO categoria) {
        categoria.setId(null); 
        Categoria newCategoria= repository.save(modelMapper.map(categoria, Categoria.class));
        return modelMapper.map(newCategoria, CategoriaDTO.class);
    }

    @Transactional
    @Override
    public CategoriaDTO update(Long id, CategoriaDTO categoria) {
        Categoria categoriaDb =repository.findById(id).orElseThrow(()-> new DataNotFoundException("La categoria que desea actualizar no existe"));
        categoriaDb.setNombre(categoria.getNombre());
        categoriaDb.setDescripcion(categoria.getDescripcion());
        
        return modelMapper.map(repository.save(categoriaDb),CategoriaDTO.class);
        
    }

    @Transactional(readOnly = true)
    @Override
    public List<CategoriaDTO> findAll() {
        List<Categoria> categorias = (List<Categoria>) repository.findAll();
        List<CategoriaDTO> categoriasDTO = new ArrayList<>();
        categorias.forEach(categoria-> categoriasDTO.add(modelMapper.map(categoria, CategoriaDTO.class)));
        return categoriasDTO;
    }

    @Transactional(readOnly = true)
    @Override
    public CategoriaDTO findById(Long id) {
        Categoria categoriaDb = repository.findById(id).orElseThrow(()-> new DataNotFoundException("La categoria no existe"));
        return modelMapper.map(categoriaDb, CategoriaDTO.class);
    }

    @Transactional
    @Override
    public CategoriaDTO delete(Long id){
        Categoria categoriaDb =repository.findById(id).orElseThrow(()-> new DataNotFoundException("La categoria que desea eliminar no existe"));
        repository.delete(categoriaDb);
        return modelMapper.map(categoriaDb, CategoriaDTO.class);
    }

}
