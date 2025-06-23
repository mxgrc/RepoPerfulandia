package com.Perfulandia.perfulandia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Perfulandia.perfulandia.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}