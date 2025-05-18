package com.Perfulandia.perfulandia.sucursal.controller;

import com.Perfulandia.perfulandia.sucursal.model.Sucursal;
import com.Perfulandia.perfulandia.sucursal.service.SucursalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sucursales")
public class SucursalController {

    @Autowired
    private SucursalService sucursalService;

    @GetMapping
    public ResponseEntity<List<Sucursal>> listar() {
        List<Sucursal> sucursales = sucursalService.findAll();
        return sucursales.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(sucursales);
    }

    @PostMapping
    public ResponseEntity<Sucursal> guardar(@RequestBody Sucursal sucursal) {
        return ResponseEntity.status(HttpStatus.CREATED).body(sucursalService.save(sucursal));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sucursal> buscar(@PathVariable Long id) {
        Sucursal sucursal = sucursalService.findById(id);
        return (sucursal != null) ? ResponseEntity.ok(sucursal) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sucursal> actualizar(@PathVariable Long id, @RequestBody Sucursal sucursal) {
        Sucursal existente = sucursalService.findById(id);
        if (existente != null) {
            existente.setNombre(sucursal.getNombre());
            existente.setDireccion(sucursal.getDireccion());
            existente.setCiudad(sucursal.getCiudad());
            return ResponseEntity.ok(sucursalService.save(existente));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        Sucursal sucursal = sucursalService.findById(id);
        if (sucursal != null) {
            sucursalService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
