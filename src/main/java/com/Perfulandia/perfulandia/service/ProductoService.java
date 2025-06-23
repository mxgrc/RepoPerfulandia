package com.Perfulandia.perfulandia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Perfulandia.perfulandia.model.Producto;
import com.Perfulandia.perfulandia.repository.ProductoRepository;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository repository;

    public List<Producto> findAll() {
        return repository.findAll();
    }

    public Producto findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Producto save(Producto producto) {
        return repository.save(producto);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
