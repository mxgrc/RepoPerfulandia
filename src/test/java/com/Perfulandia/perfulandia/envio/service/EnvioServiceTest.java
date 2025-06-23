package com.Perfulandia.perfulandia.envio.service;


import com.Perfulandia.perfulandia.model.Envio;
import com.Perfulandia.perfulandia.repository.EnvioRepository;
import com.Perfulandia.perfulandia.service.EnvioService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class EnvioServiceTest {

    @Mock
    private EnvioRepository repository;

    @InjectMocks
    private EnvioService envioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        Envio envio1 = new Envio();
        Envio envio2 = new Envio();
        when(repository.findAll()).thenReturn(Arrays.asList(envio1, envio2));

        List<Envio> result = envioService.findAll();

        assertEquals(2, result.size());
        verify(repository).findAll();
    }

    @Test
    void testFindById_Existe() {
        Envio envio = new Envio();
        envio.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(envio));

        Envio result = envioService.findById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void testFindById_NoExiste() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        Envio result = envioService.findById(1L);

        assertNull(result);
    }

    @Test
    void testSave() {
        Envio envio = new Envio();
        when(repository.save(envio)).thenReturn(envio);

        Envio result = envioService.save(envio);

        assertNotNull(result);
        verify(repository).save(envio);
    }

    @Test
    void testDelete() {
        doNothing().when(repository).deleteById(1L);

        envioService.delete(1L);

        verify(repository).deleteById(1L);
    }
}
