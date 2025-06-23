package com.Perfulandia.perfulandia.controller;

import com.Perfulandia.perfulandia.model.Sucursal;
import com.Perfulandia.perfulandia.service.SucursalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Sucursales", description = "Operaciones sobre sucursales en Perfulandia")
@RestController
@RequestMapping("/api/v1/sucursales")
public class SucursalController {

    @Autowired
    private SucursalService sucursalService;

    @Operation(summary = "Listar todas las sucursales", description = "Obtiene la lista completa de sucursales")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucursales obtenidas correctamente"),
            @ApiResponse(responseCode = "204", description = "No hay sucursales disponibles")
    })
    @GetMapping
    public ResponseEntity<List<Sucursal>> listar() {
        List<Sucursal> sucursales = sucursalService.findAll();
        return sucursales.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(sucursales);
    }

    @Operation(summary = "Registrar una nueva sucursal", description = "Registra una nueva sucursal en el sistema")
    @ApiResponse(responseCode = "201", description = "Sucursal creada exitosamente")
    @PostMapping
    public ResponseEntity<Sucursal> guardar(@RequestBody Sucursal sucursal) {
        return ResponseEntity.status(HttpStatus.CREATED).body(sucursalService.save(sucursal));
    }

    @Operation(summary = "Buscar una sucursal por ID", description = "Obtiene los detalles de una sucursal específica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucursal encontrada"),
            @ApiResponse(responseCode = "404", description = "Sucursal no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Sucursal> buscar(@PathVariable Long id) {
        Sucursal sucursal = sucursalService.findById(id);
        return (sucursal != null) ? ResponseEntity.ok(sucursal) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Actualizar una sucursal", description = "Actualiza los datos de una sucursal existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucursal actualizada correctamente"),
            @ApiResponse(responseCode = "404", description = "Sucursal no encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Sucursal> actualizar(@PathVariable Long id, @RequestBody Sucursal sucursal) {
        Sucursal existente = sucursalService.findById(id);
        if (existente != null) {
            existente.setNombre(sucursal.getNombre());
            existente.setDireccion(sucursal.getDireccion());
            existente.setCiudad(sucursal.getCiudad());
            return ResponseEntity.ok(sucursalService.save(existente));
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Eliminar una sucursal", description = "Elimina una sucursal por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Sucursal eliminada correctamente"),
            @ApiResponse(responseCode = "404", description = "Sucursal no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        Sucursal sucursal = sucursalService.findById(id);
        if (sucursal != null) {
            sucursalService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
