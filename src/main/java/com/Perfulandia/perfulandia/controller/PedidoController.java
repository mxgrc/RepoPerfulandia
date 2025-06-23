package com.Perfulandia.perfulandia.controller;

import com.Perfulandia.perfulandia.model.Pedido;
import com.Perfulandia.perfulandia.service.PedidoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<List<Pedido>> listar() {
        List<Pedido> pedidos = pedidoService.findAll();
        return pedidos.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(pedidos);
    }

    @PostMapping
    public ResponseEntity<Pedido> guardar(@RequestBody Pedido pedido) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.save(pedido));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscar(@PathVariable Long id) {
        Pedido pedido = pedidoService.findById(id);
        return (pedido != null) ? ResponseEntity.ok(pedido) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> actualizar(@PathVariable Long id, @RequestBody Pedido pedido) {
        Pedido existente = pedidoService.findById(id);
        if (existente != null) {
            existente.setClienteNombre(pedido.getClienteNombre());
            existente.setFecha(pedido.getFecha());
            existente.setTotal(pedido.getTotal());
            existente.setEstado(pedido.getEstado());
            return ResponseEntity.ok(pedidoService.save(existente));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        Pedido pedido = pedidoService.findById(id);
        if (pedido != null) {
            pedidoService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
