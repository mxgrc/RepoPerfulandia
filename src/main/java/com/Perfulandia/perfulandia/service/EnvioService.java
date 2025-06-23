package com.Perfulandia.perfulandia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Perfulandia.perfulandia.model.Envio;
import com.Perfulandia.perfulandia.repository.EnvioRepository;

@Service
public class EnvioService {

    @Autowired
    private EnvioRepository repository;

    public List<Envio> findAll() {
        return repository.findAll();
    }

    public Envio findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Envio save(Envio envio) {
        return repository.save(envio);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
