package com.Perfulandia.perfulandia.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.Perfulandia.perfulandia.model.Producto;
import com.Perfulandia.perfulandia.repository.ProductoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ProductoServiceTest {

    @InjectMocks
    private ProductoService productoService;

    @Mock
    private ProductoRepository productoRepository;

    @Test
    void testFindAll() {
        Producto p = new Producto(1L, "Perfume A", "Descripción A", 10, BigDecimal.valueOf(15000));
        when(productoRepository.findAll()).thenReturn(List.of(p));

        List<Producto> productos = productoService.findAll();

        assertNotNull(productos);
        assertEquals(1, productos.size());
        assertEquals("Perfume A", productos.get(0).getNombre());
    }

    @Test
    void testFindById() {
        Long id = 1L;
        Producto producto = new Producto(id, "Perfume A", "Descripción A", 10, BigDecimal.valueOf(15000));

        when(productoRepository.findById(id)).thenReturn(Optional.of(producto));

        Producto found = productoService.findById(id);

        assertNotNull(found);
        assertEquals(id, found.getId());
        assertEquals("Perfume A", found.getNombre());
    }

    @Test
    void testSave() {
        Producto producto = new Producto(null, "Perfume B", "Descripción B", 5, BigDecimal.valueOf(25000));
        Producto productoGuardado = new Producto(2L, "Perfume B", "Descripción B", 5, BigDecimal.valueOf(25000));

        when(productoRepository.save(producto)).thenReturn(productoGuardado);

        Producto saved = productoService.save(producto);

        assertNotNull(saved);
        assertEquals(2L, saved.getId());
        assertEquals("Perfume B", saved.getNombre());
    }

    @Test
    void testDelete() {
        Long id = 1L;

        doNothing().when(productoRepository).deleteById(id);

        productoService.delete(id);

        verify(productoRepository, times(1)).deleteById(id);
    }
}
