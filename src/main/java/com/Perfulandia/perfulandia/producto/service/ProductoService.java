package com.Perfulandia.perfulandia.producto.service;

import com.Perfulandia.perfulandia.producto.model.Producto;
import com.Perfulandia.perfulandia.producto.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
