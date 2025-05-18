package com.Perfulandia.perfulandia.pedido.repository;

import com.Perfulandia.perfulandia.pedido.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}