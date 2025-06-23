package com.Perfulandia.perfulandia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Perfulandia.perfulandia.model.Sucursal;
import com.Perfulandia.perfulandia.repository.SucursalRepository;

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
