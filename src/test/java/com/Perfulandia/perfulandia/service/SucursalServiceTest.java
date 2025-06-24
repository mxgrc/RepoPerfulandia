package com.Perfulandia.perfulandia.service;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.Perfulandia.perfulandia.model.Sucursal;
import com.Perfulandia.perfulandia.repository.SucursalRepository;

@ExtendWith(MockitoExtension.class)
class SucursalServiceTest {

    @InjectMocks
    private SucursalService sucursalService;

    @Mock
    private SucursalRepository sucursalRepository;

    @Test
    void testFindAll() {
        Sucursal s = new Sucursal(1L, "Sucursal Centro", "Av Central 123", "CiudadX", "123456789");
        when(sucursalRepository.findAll()).thenReturn(List.of(s));

        List<Sucursal> sucursales = sucursalService.findAll();

        assertNotNull(sucursales);
        assertEquals(1, sucursales.size());
        assertEquals("Sucursal Centro", sucursales.get(0).getNombre());
    }

    @Test
    void testFindById() {
        Long id = 1L;
        Sucursal sucursal = new Sucursal(id, "Sucursal Centro", "Av Central 123", "CiudadX", "123456789");

        when(sucursalRepository.findById(id)).thenReturn(Optional.of(sucursal));

        Sucursal found = sucursalService.findById(id);

        assertNotNull(found);
        assertEquals(id, found.getId());
        assertEquals("Sucursal Centro", found.getNombre());
    }

    @Test
    void testSave() {
        Sucursal sucursal = new Sucursal(null, "Sucursal Norte", "Av Norte 456", "CiudadY", "987654321");
        Sucursal sucursalGuardada = new Sucursal(2L, "Sucursal Norte", "Av Norte 456", "CiudadY", "987654321");

        when(sucursalRepository.save(sucursal)).thenReturn(sucursalGuardada);

        Sucursal saved = sucursalService.save(sucursal);

        assertNotNull(saved);
        assertEquals(2L, saved.getId());
        assertEquals("Sucursal Norte", saved.getNombre());
    }

    @Test
    void testDelete() {
        Long id = 1L;

        doNothing().when(sucursalRepository).deleteById(id);

        sucursalService.delete(id);

        verify(sucursalRepository, times(1)).deleteById(id);
    }
}
