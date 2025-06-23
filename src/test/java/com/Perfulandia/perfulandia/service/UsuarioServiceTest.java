package com.Perfulandia.perfulandia.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.Perfulandia.perfulandia.model.Usuario;
import com.Perfulandia.perfulandia.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Test
    void testFindAll() {
        Usuario u = new Usuario(1L, "juan", "1234", "ADMIN");
        when(usuarioRepository.findAll()).thenReturn(List.of(u));

        List<Usuario> usuarios = usuarioService.findAll();

        assertNotNull(usuarios);
        assertEquals(1, usuarios.size());
        assertEquals("juan", usuarios.get(0).getUsername());
    }

    @Test
    void testFindById() {
        Long id = 1L;
        Usuario usuario = new Usuario(id, "maria", "pass", "USER");

        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuario));

        Usuario found = usuarioService.findById(id);

        assertNotNull(found);
        assertEquals(id, found.getId());
        assertEquals("maria", found.getUsername());
    }

    @Test
    void testSave() {
        Usuario usuario = new Usuario(null, "pedro", "clave", "USER");
        Usuario usuarioGuardado = new Usuario(2L, "pedro", "clave", "USER");

        when(usuarioRepository.save(usuario)).thenReturn(usuarioGuardado);

        Usuario saved = usuarioService.save(usuario);

        assertNotNull(saved);
        assertEquals(2L, saved.getId());
        assertEquals("pedro", saved.getUsername());
    }

    @Test
    void testDelete() {
        Long id = 1L;

        doNothing().when(usuarioRepository).deleteById(id);

        usuarioService.delete(id);

        verify(usuarioRepository, times(1)).deleteById(id);
    }
}
