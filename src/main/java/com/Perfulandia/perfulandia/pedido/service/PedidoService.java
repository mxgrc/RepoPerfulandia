package com.Perfulandia.perfulandia.pedido.service;

import com.Perfulandia.perfulandia.pedido.model.Pedido;
import com.Perfulandia.perfulandia.pedido.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    public List<Pedido> findAll() {
        return repository.findAll();
    }

    public Pedido findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Pedido save(Pedido pedido) {
        return repository.save(pedido);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
