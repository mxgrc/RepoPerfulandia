package com.Perfulandia.perfulandia.envio.service;

import com.Perfulandia.perfulandia.envio.model.Envio;
import com.Perfulandia.perfulandia.envio.repository.EnvioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
