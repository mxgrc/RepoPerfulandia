package com.Perfulandia.perfulandia.pedido.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "pedidos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String clienteNombre;

    private Date fecha;

    private Double total;

    private String estado; // EJ: PENDIENTE, ENVIADO, ENTREGADO
}
