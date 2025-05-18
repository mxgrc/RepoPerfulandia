package com.Perfulandia.perfulandia.envio.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "envios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Envio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date fechaSalida;

    private Date fechaLlegada;

    private String estado; // Ej: EN RUTA, ENTREGADO, CANCELADO

    private String direccionDestino;
}
