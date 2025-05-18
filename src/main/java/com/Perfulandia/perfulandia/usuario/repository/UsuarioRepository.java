package com.Perfulandia.perfulandia.usuario.repository;

import com.Perfulandia.perfulandia.usuario.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}