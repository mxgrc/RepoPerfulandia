package com.Perfulandia.perfulandia.usuario.controller;

import com.Perfulandia.perfulandia.usuario.model.Usuario;
import com.Perfulandia.perfulandia.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> listar() {
        List<Usuario> usuarios = usuarioService.findAll();
        return usuarios.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(usuarios);
    }

    @PostMapping
    public ResponseEntity<Usuario> guardar(@RequestBody Usuario usuario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuario));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscar(@PathVariable Long id) {
        Usuario usuario = usuarioService.findById(id);
        return (usuario != null) ? ResponseEntity.ok(usuario) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
        Usuario existente = usuarioService.findById(id);
        if (existente != null) {
            existente.setUsername(usuario.getUsername());
            existente.setPassword(usuario.getPassword());
            existente.setRol(usuario.getRol());
            return ResponseEntity.ok(usuarioService.save(existente));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        Usuario usuario = usuarioService.findById(id);
        if (usuario != null) {
            usuarioService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
