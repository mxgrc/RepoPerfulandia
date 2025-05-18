package com.Perfulandia.perfulandia.producto.repository;

import com.Perfulandia.perfulandia.producto.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}