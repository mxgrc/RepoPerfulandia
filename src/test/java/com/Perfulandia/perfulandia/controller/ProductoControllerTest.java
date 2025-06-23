package com.Perfulandia.perfulandia.controller;

import com.Perfulandia.perfulandia.model.Producto;
import com.Perfulandia.perfulandia.service.ProductoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class ProductoControllerTest {

    @InjectMocks
    private ProductoController productoController;

    @Mock
    private ProductoService productoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listarProductos_conProductosDevuelve200YLista() {
        Producto p1 = new Producto(); p1.setId(1L); p1.setNombre("Producto 1");
        Producto p2 = new Producto(); p2.setId(2L); p2.setNombre("Producto 2");

        when(productoService.findAll()).thenReturn(Arrays.asList(p1, p2));

        ResponseEntity<List<Producto>> response = productoController.listar();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        verify(productoService).findAll();
    }

    @Test
    void listarProductos_sinProductosDevuelve204() {
        when(productoService.findAll()).thenReturn(Collections.emptyList());

        ResponseEntity<List<Producto>> response = productoController.listar();

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
        verify(productoService).findAll();
    }

    @Test
    void buscarProducto_existenteDevuelve200YEntidad() {
        Producto producto = new Producto();
        producto.setId(1L);
        producto.setNombre("Producto Existente");
        when(productoService.findById(1L)).thenReturn(producto);

        ResponseEntity<Producto> response = productoController.buscar(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Producto Existente", response.getBody().getNombre());
        verify(productoService).findById(1L);
    }

    @Test
    void buscarProducto_noExistenteDevuelve404() {
        when(productoService.findById(1L)).thenReturn(null);

        ResponseEntity<Producto> response = productoController.buscar(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(productoService).findById(1L);
    }

    @Test
    void guardarProducto_devuelve201YEntidadCreada() {
        Producto producto = new Producto();
        producto.setNombre("Nuevo Producto");
        when(productoService.save(producto)).thenReturn(producto);

        ResponseEntity<Producto> response = productoController.guardar(producto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Nuevo Producto", response.getBody().getNombre());
        verify(productoService).save(producto);
    }

    @Test
    void actualizarProducto_existenteDevuelve200YEntidadActualizada() {
        Producto existente = new Producto();
        existente.setId(1L);
        existente.setNombre("Producto Original");
        existente.setPrecio(BigDecimal.valueOf(100.0));
        existente.setDescripcion("Descripcion Original");

        Producto actualizada = new Producto();
        actualizada.setNombre("Producto Actualizado");
        actualizada.setPrecio(BigDecimal.valueOf(150.0));
        actualizada.setDescripcion("Descripcion Actualizada");

        when(productoService.findById(1L)).thenReturn(existente);
        when(productoService.save(any(Producto.class))).thenAnswer(invocation -> invocation.getArgument(0));

        ResponseEntity<Producto> response = productoController.actualizar(1L, actualizada);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Producto Actualizado", response.getBody().getNombre());
        assertEquals(BigDecimal.valueOf(150.0), response.getBody().getPrecio());
        assertEquals("Descripcion Actualizada", response.getBody().getDescripcion());

        verify(productoService).findById(1L);
        verify(productoService).save(any(Producto.class));
    }

    @Test
    void actualizarProducto_noExistenteDevuelve404() {
        when(productoService.findById(1L)).thenReturn(null);

        ResponseEntity<Producto> response = productoController.actualizar(1L, new Producto());

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(productoService).findById(1L);
    }

    @Test
    void eliminarProducto_existenteDevuelve204() {
        Producto producto = new Producto();
        producto.setId(1L);
        when(productoService.findById(1L)).thenReturn(producto);
        doNothing().when(productoService).delete(1L);

        ResponseEntity<Void> response = productoController.eliminar(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(productoService).findById(1L);
        verify(productoService).delete(1L);
    }

    @Test
    void eliminarProducto_noExistenteDevuelve404() {
        when(productoService.findById(1L)).thenReturn(null);

        ResponseEntity<Void> response = productoController.eliminar(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(productoService).findById(1L);
        verify(productoService, never()).delete(anyLong());
    }
}
