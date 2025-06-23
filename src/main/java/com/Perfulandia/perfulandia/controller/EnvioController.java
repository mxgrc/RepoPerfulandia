package com.Perfulandia.perfulandia.controller;

import java.util.List;

import com.Perfulandia.perfulandia.model.Envio;
import com.Perfulandia.perfulandia.service.EnvioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Envios", description = "Operaciones sobre envíos en Perfulandia")
@RestController
@RequestMapping("/api/v1/envios")
public class EnvioController {

    @Autowired
    private EnvioService envioService;

    @Operation(summary = "Listar todos los envíos", description = "Obtiene la lista completa de envíos registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Envíos obtenidos correctamente"),
            @ApiResponse(responseCode = "204", description = "No hay envíos disponibles")
    })
    @GetMapping
    public ResponseEntity<List<Envio>> listar() {
        List<Envio> envios = envioService.findAll();
        return envios.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(envios);
    }

    @Operation(summary = "Registrar un nuevo envío", description = "Registra un nuevo envío en el sistema")
    @ApiResponse(responseCode = "201", description = "Envío creado exitosamente")
    @PostMapping
    public ResponseEntity<Envio> guardar(@RequestBody Envio envio) {
        return ResponseEntity.status(HttpStatus.CREATED).body(envioService.save(envio));
    }

    @Operation(summary = "Buscar un envío por ID", description = "Obtiene los detalles de un envío específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Envío encontrado"),
            @ApiResponse(responseCode = "404", description = "Envío no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Envio> buscar(@PathVariable Long id) {
        Envio envio = envioService.findById(id);
        return (envio != null) ? ResponseEntity.ok(envio) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Actualizar un envío", description = "Actualiza los datos de un envío existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Envío actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Envío no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Envio> actualizar(@PathVariable Long id, @RequestBody Envio envio) {
        Envio existente = envioService.findById(id);
        if (existente != null) {
            existente.setFechaSalida(envio.getFechaSalida());
            existente.setFechaLlegada(envio.getFechaLlegada());
            existente.setEstado(envio.getEstado());
            existente.setDireccionDestino(envio.getDireccionDestino());
            return ResponseEntity.ok(envioService.save(existente));
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Eliminar un envío", description = "Elimina un envío por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Envío eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Envío no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        Envio envio = envioService.findById(id);
        if (envio != null) {
            envioService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
