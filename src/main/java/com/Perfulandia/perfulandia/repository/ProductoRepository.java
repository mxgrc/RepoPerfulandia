package com.Perfulandia.perfulandia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Perfulandia.perfulandia.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}