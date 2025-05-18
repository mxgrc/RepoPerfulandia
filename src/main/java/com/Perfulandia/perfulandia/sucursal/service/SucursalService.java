package com.Perfulandia.perfulandia.sucursal.service;

import com.Perfulandia.perfulandia.sucursal.model.Sucursal;
import com.Perfulandia.perfulandia.sucursal.repository.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SucursalService {

    @Autowired
    private SucursalRepository repository;

    public List<Sucursal> findAll() {
        return repository.findAll();
    }

    public Sucursal findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Sucursal save(Sucursal sucursal) {
        return repository.save(sucursal);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
