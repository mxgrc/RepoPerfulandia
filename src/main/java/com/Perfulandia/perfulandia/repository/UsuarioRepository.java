package com.Perfulandia.perfulandia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Perfulandia.perfulandia.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}