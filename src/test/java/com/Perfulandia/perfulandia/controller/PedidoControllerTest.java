package com.Perfulandia.perfulandia.controller;


import com.Perfulandia.perfulandia.model.Pedido;
import com.Perfulandia.perfulandia.service.PedidoService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PedidoControllerTest {

    @InjectMocks
    private PedidoController pedidoController;

    @Mock
    private PedidoService pedidoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListar() {
        Pedido p1 = new Pedido();
        Pedido p2 = new Pedido();
        when(pedidoService.findAll()).thenReturn(Arrays.asList(p1, p2));

        ResponseEntity<List<Pedido>> response = pedidoController.listar();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void testGuardar() {
        Pedido nuevo = new Pedido();
        nuevo.setId(1L);

        when(pedidoService.save(any(Pedido.class))).thenReturn(nuevo);

        ResponseEntity<Pedido> response = pedidoController.guardar(nuevo);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals(1L, response.getBody().getId());
    }

    @Test
    void testBuscarExistente() {
        Pedido pedido = new Pedido();
        pedido.setId(1L);

        when(pedidoService.findById(1L)).thenReturn(pedido);

        ResponseEntity<Pedido> response = pedidoController.buscar(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1L, response.getBody().getId());
    }

    @Test
    void testBuscarNoExistente() {
        when(pedidoService.findById(999L)).thenReturn(null);

        ResponseEntity<Pedido> response = pedidoController.buscar(999L);

        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void testEliminar() {
        Pedido pedido = new Pedido();
        pedido.setId(1L);
        when(pedidoService.findById(1L)).thenReturn(pedido);

        ResponseEntity<Void> response = pedidoController.eliminar(1L);

        assertEquals(204, response.getStatusCodeValue());
        verify(pedidoService).delete(1L);
    }

    @Test
    void testActualizar() {
        Pedido existente = new Pedido();
        existente.setId(1L);
        existente.setEstado("Pendiente");

        Pedido actualizado = new Pedido();
        actualizado.setEstado("Enviado");

        when(pedidoService.findById(1L)).thenReturn(existente);
        when(pedidoService.save(any(Pedido.class))).thenReturn(existente);

        ResponseEntity<Pedido> response = pedidoController.actualizar(1L, actualizado);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Enviado", response.getBody().getEstado());
    }
}
