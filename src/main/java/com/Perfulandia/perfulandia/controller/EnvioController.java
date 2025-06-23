package com.Perfulandia.perfulandia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Perfulandia.perfulandia.model.Envio;
import com.Perfulandia.perfulandia.service.EnvioService;

@RestController
@RequestMapping("/api/v1/envios")
public class EnvioController {

    @Autowired
    private EnvioService envioService;

    @GetMapping
    public ResponseEntity<List<Envio>> listar() {
        List<Envio> envios = envioService.findAll();
        return envios.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(envios);
    }

    @PostMapping
    public ResponseEntity<Envio> guardar(@RequestBody Envio envio) {
        return ResponseEntity.status(HttpStatus.CREATED).body(envioService.save(envio));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Envio> buscar(@PathVariable Long id) {
        Envio envio = envioService.findById(id);
        return (envio != null) ? ResponseEntity.ok(envio) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Envio> actualizar(@PathVariable Long id, @RequestBody Envio envio) {
        Envio existente = envioService.findById(id);
        if (existente != null) {
            existente.setFechaSalida(envio.getFechaSalida());
            existente.setFechaLlegada(envio.getFechaLlegada());
            existente.setEstado(envio.getEstado());
            existente.setDireccionDestino(envio.getDireccionDestino());
            return ResponseEntity.ok(envioService.save(existente));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        Envio envio = envioService.findById(id);
        if (envio != null) {
            envioService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
