package com.Perfulandia.perfulandia.controller;

import com.Perfulandia.perfulandia.model.Sucursal;
import com.Perfulandia.perfulandia.service.SucursalService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SucursalControllerTest {

    @InjectMocks
    private SucursalController sucursalController;

    @Mock
    private SucursalService sucursalService;

    @Test
    void listarSucursales_conSucursalesDevuelve200YLista() {
        Sucursal s1 = new Sucursal();
        s1.setId(1L);
        s1.setNombre("Sucursal 1");
        Sucursal s2 = new Sucursal();
        s2.setId(2L);
        s2.setNombre("Sucursal 2");

        when(sucursalService.findAll()).thenReturn(Arrays.asList(s1, s2));

        ResponseEntity<List<Sucursal>> response = sucursalController.listar();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        verify(sucursalService).findAll();
    }

    @Test
    void listarSucursales_sinSucursalesDevuelve204() {
        when(sucursalService.findAll()).thenReturn(Collections.emptyList());

        ResponseEntity<List<Sucursal>> response = sucursalController.listar();

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
        verify(sucursalService).findAll();
    }

    @Test
    void buscarSucursal_existenteDevuelve200YEntidad() {
        Sucursal sucursal = new Sucursal();
        sucursal.setId(1L);
        sucursal.setNombre("Sucursal Existente");

        when(sucursalService.findById(1L)).thenReturn(sucursal);

        ResponseEntity<Sucursal> response = sucursalController.buscar(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Sucursal Existente", response.getBody().getNombre());
        verify(sucursalService).findById(1L);
    }

    @Test
    void buscarSucursal_noExistenteDevuelve404() {
        when(sucursalService.findById(1L)).thenReturn(null);

        ResponseEntity<Sucursal> response = sucursalController.buscar(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(sucursalService).findById(1L);
    }

    @Test
    void guardarSucursal_devuelve201YEntidadCreada() {
        Sucursal sucursal = new Sucursal();
        sucursal.setNombre("Nueva Sucursal");

        when(sucursalService.save(sucursal)).thenReturn(sucursal);

        ResponseEntity<Sucursal> response = sucursalController.guardar(sucursal);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Nueva Sucursal", response.getBody().getNombre());
        verify(sucursalService).save(sucursal);
    }

    @Test
    void actualizarSucursal_existenteDevuelve200YEntidadActualizada() {
        Sucursal existente = new Sucursal();
        existente.setId(1L);
        existente.setNombre("Sucursal Original");
        existente.setDireccion("Dir original");
        existente.setCiudad("Ciudad original");
        existente.setTelefono("123456");

        Sucursal actualizada = new Sucursal();
        actualizada.setNombre("Sucursal Actualizada");
        actualizada.setDireccion("Dir actualizada");
        actualizada.setCiudad("Ciudad actualizada");
        actualizada.setTelefono("654321");

        when(sucursalService.findById(1L)).thenReturn(existente);
        when(sucursalService.save(any(Sucursal.class))).thenAnswer(inv -> inv.getArgument(0));

        ResponseEntity<Sucursal> response = sucursalController.actualizar(1L, actualizada);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Sucursal Actualizada", response.getBody().getNombre());
        assertEquals("Dir actualizada", response.getBody().getDireccion());
        assertEquals("Ciudad actualizada", response.getBody().getCiudad());
        assertEquals("123456", response.getBody().getTelefono());

        verify(sucursalService).findById(1L);
        verify(sucursalService).save(any(Sucursal.class));
    }

    @Test
    void actualizarSucursal_noExistenteDevuelve404() {
        when(sucursalService.findById(1L)).thenReturn(null);

        ResponseEntity<Sucursal> response = sucursalController.actualizar(1L, new Sucursal());

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(sucursalService).findById(1L);
    }

    @Test
    void eliminarSucursal_existenteDevuelve204() {
        Sucursal sucursal = new Sucursal();
        sucursal.setId(1L);

        when(sucursalService.findById(1L)).thenReturn(sucursal);
        doNothing().when(sucursalService).delete(1L);

        ResponseEntity<Void> response = sucursalController.eliminar(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(sucursalService).findById(1L);
        verify(sucursalService).delete(1L);
    }

    @Test
    void eliminarSucursal_noExistenteDevuelve404() {
        when(sucursalService.findById(1L)).thenReturn(null);

        ResponseEntity<Void> response = sucursalController.eliminar(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(sucursalService).findById(1L);
        verify(sucursalService, never()).delete(anyLong());
    }
}
