package com.Perfulandia.perfulandia.sucursal.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "sucursales")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sucursal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String direccion;

    private String ciudad;
}
