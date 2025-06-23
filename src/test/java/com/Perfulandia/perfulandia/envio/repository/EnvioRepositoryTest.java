package com.Perfulandia.perfulandia.envio.repository;

import com.Perfulandia.perfulandia.model.Envio;
import com.Perfulandia.perfulandia.repository.EnvioRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EnvioRepositoryTest {

    @Mock
    private EnvioRepository envioRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        Envio envio1 = new Envio();
        Envio envio2 = new Envio();
        when(envioRepository.findAll()).thenReturn(Arrays.asList(envio1, envio2));

        List<Envio> envios = envioRepository.findAll();

        assertEquals(2, envios.size());
        verify(envioRepository).findAll();
    }

    @Test
    void testFindById_Exists() {
        Envio envio = new Envio();
        envio.setId(1L);
        when(envioRepository.findById(1L)).thenReturn(Optional.of(envio));

        Optional<Envio> result = envioRepository.findById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
    }

    @Test
    void testFindById_NotExists() {
        when(envioRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Envio> result = envioRepository.findById(1L);

        assertFalse(result.isPresent());
    }

    @Test
    void testSave() {
        Envio envio = new Envio();
        when(envioRepository.save(envio)).thenReturn(envio);

        Envio saved = envioRepository.save(envio);

        assertNotNull(saved);
        verify(envioRepository).save(envio);
    }

    @Test
    void testDeleteById() {
        doNothing().when(envioRepository).deleteById(1L);

        envioRepository.deleteById(1L);

        verify(envioRepository).deleteById(1L);
    }
}
