package com.example.testing.services;

import com.example.testing.builder.LibroBuilder;
import com.example.testing.entities.Autor;
import com.example.testing.entities.Libro;
import com.example.testing.repositories.LibroRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(value = {MockitoExtension.class})
class LibroServiceTest {

    @Mock
    private LibroRepository libroRepository;

    @InjectMocks
    private LibroService libroService;

    @Mock
    private Libro libro;
    private Libro libro2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        libro = Libro.builder().id(1L).nombre("El señor de los Anillos").isbn("Z 666").autor(new Autor(1L, "PEPE LUI", "pepe@yahoo.com")).build();
        libro2 = Libro.builder().id(2L).nombre("Harry Potter").isbn("I 468").autor(new Autor(2L, "LUCA PRODAN", " luca@gmail.com")).build();
    }

    @Test
    void findAllTest() throws Exception {
        Mockito.when(libroService.findAll()).thenReturn(Arrays.asList(libro));
        Assertions.assertEquals(libroService.findAll(), Arrays.asList(libro));
        Assertions.assertNotNull(libroService.findAll());
    }

    @Test
    void findByIdTest() throws Exception {
        when(libroRepository.findById(1L)).thenReturn(Optional.ofNullable(libro));
        Assertions.assertEquals("PEPE LUI 2",libroService.findById(1L).getNombre());
    }

    @Test
    void saveTest() throws Exception {
        when(libroRepository.save(any(Libro.class))).thenReturn(libro);
        Assertions.assertNotNull(libroService.save(new Libro()));
    }

    @Test
    void updateTest() throws Exception {
    }

    @Test
    void delete() throws Exception {
        System.out.println(libro.getNombre() + "\n" + libro.getId());
    }

}