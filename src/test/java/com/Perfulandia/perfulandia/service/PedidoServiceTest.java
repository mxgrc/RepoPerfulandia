package com.Perfulandia.perfulandia.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.Perfulandia.perfulandia.model.Pedido;
import com.Perfulandia.perfulandia.repository.PedidoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class PedidoServiceTest {

    @InjectMocks
    private PedidoService pedidoService;

    @Mock
    private PedidoRepository pedidoRepository;

    @Test
    void testFindAll() {
        Pedido p = new Pedido(1L, "Cliente 1", LocalDate.now(), BigDecimal.valueOf(1000), "PENDIENTE");
        when(pedidoRepository.findAll()).thenReturn(List.of(p));

        List<Pedido> pedidos = pedidoService.findAll();

        assertNotNull(pedidos);
        assertEquals(1, pedidos.size());
        assertEquals("Cliente 1", pedidos.get(0).getClienteNombre());
    }

    @Test
    void testFindById() {
        Long id = 1L;
        Pedido pedido = new Pedido(id, "Cliente 1", LocalDate.now(), BigDecimal.valueOf(1000), "PENDIENTE");

        when(pedidoRepository.findById(id)).thenReturn(Optional.of(pedido));

        Pedido found = pedidoService.findById(id);

        assertNotNull(found);
        assertEquals(id, found.getId());
        assertEquals("Cliente 1", found.getClienteNombre());
    }

    @Test
    void testSave() {
        Pedido pedido = new Pedido(null, "Cliente 2", LocalDate.now(), BigDecimal.valueOf(2000), "ENVIADO");

        Pedido pedidoGuardado = new Pedido(2L, "Cliente 2", LocalDate.now(), BigDecimal.valueOf(2000), "ENVIADO");

        when(pedidoRepository.save(pedido)).thenReturn(pedidoGuardado);

        Pedido saved = pedidoService.save(pedido);

        assertNotNull(saved);
        assertEquals(2L, saved.getId());
        assertEquals("Cliente 2", saved.getClienteNombre());
    }

    @Test
    void testDelete() {
        Long id = 1L;

        doNothing().when(pedidoRepository).deleteById(id);

        pedidoService.delete(id);

        verify(pedidoRepository, times(1)).deleteById(id);
    }
}
