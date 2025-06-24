package com.Perfulandia.perfulandia.controller;

import com.Perfulandia.perfulandia.model.Envio;
import com.Perfulandia.perfulandia.service.EnvioService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EnvioController.class)
class EnvioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EnvioService envioService;

    private Envio envio;

    @BeforeEach
    void setUp() {
        envio = new Envio();
        envio.setId(1L);
        envio.setDireccionDestino("Calle Falsa 123");
        envio.setEstado("En tránsito");
    }

    @Test
    void testListarEnvios() throws Exception {
        when(envioService.findAll()).thenReturn(Arrays.asList(envio));

        mockMvc.perform(get("/api/v1/envios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].direccionDestino").value("Calle Falsa 123"));
    }

    @Test
    void testListarEnviosVacio() throws Exception {
        when(envioService.findAll()).thenReturn(Arrays.asList());

        mockMvc.perform(get("/api/v1/envios"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testBuscarEnvioPorId_Existe() throws Exception {
        when(envioService.findById(1L)).thenReturn(envio);

        mockMvc.perform(get("/api/v1/envios/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.estado").value("En tránsito"));
    }

    @Test
    void testBuscarEnvioPorId_NoExiste() throws Exception {
        when(envioService.findById(1L)).thenReturn(null);

        mockMvc.perform(get("/api/v1/envios/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGuardarEnvio() throws Exception {
        when(envioService.save(any(Envio.class))).thenReturn(envio);

        mockMvc.perform(post("/api/v1/envios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(envio)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.direccionDestino").value("Calle Falsa 123"));
    }

    @Test
    void testActualizarEnvio_Existe() throws Exception {
        when(envioService.findById(1L)).thenReturn(envio);
        when(envioService.save(any(Envio.class))).thenReturn(envio);

        envio.setEstado("Entregado");

        mockMvc.perform(put("/api/v1/envios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(envio)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.estado").value("Entregado"));
    }

    @Test
    void testActualizarEnvio_NoExiste() throws Exception {
        when(envioService.findById(1L)).thenReturn(null);

        mockMvc.perform(put("/api/v1/envios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(envio)))
                .andExpect(status().isNotFound());
    }

    @Test
    void testEliminarEnvio_Existe() throws Exception {
        when(envioService.findById(1L)).thenReturn(envio);
        doNothing().when(envioService).delete(1L);

        mockMvc.perform(delete("/api/v1/envios/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testEliminarEnvio_NoExiste() throws Exception {
        when(envioService.findById(1L)).thenReturn(null);

        mockMvc.perform(delete("/api/v1/envios/1"))
                .andExpect(status().isNotFound());
    }
}
