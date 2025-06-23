package com.Perfulandia.perfulandia.controller;


import com.Perfulandia.perfulandia.model.Producto;
import com.Perfulandia.perfulandia.service.ProductoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<Producto>> listar() {
        List<Producto> productos = productoService.findAll();
        return productos.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(productos);
    }

    @PostMapping
    public ResponseEntity<Producto> guardar(@RequestBody Producto producto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.save(producto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> buscar(@PathVariable Long id) {
        Producto producto = productoService.findById(id);
        return (producto != null) ? ResponseEntity.ok(producto) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizar(@PathVariable Long id, @RequestBody Producto producto) {
        Producto existente = productoService.findById(id);
        if (existente != null) {
            existente.setNombre(producto.getNombre());
            existente.setDescripcion(producto.getDescripcion());
            existente.setStock(producto.getStock());
            existente.setPrecio(producto.getPrecio());
            return ResponseEntity.ok(productoService.save(existente));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        Producto producto = productoService.findById(id);
        if (producto != null) {
            productoService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
