package com.example.testing.controllers;

import com.example.testing.dto.LibroDto;
import com.example.testing.entities.Autor;
import com.example.testing.entities.Libro;
import com.example.testing.services.LibroService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LibroControllerTest {

//    @InjectMocks
//    private LibroController libroController;
//    @Mock
//    private LibroService libroService;

    private LibroService libroService;
    private LibroController libroController;

    private Libro libro;
    private Libro libro2;
    private LibroDto libroDto;

    private List<Libro> librosLista;
    private List<LibroDto> librosDtoList;
    @BeforeEach
    void setUp() throws Exception {
        libroService = mock(LibroService.class);
        libroController = new LibroController(libroService);

        libro = new Libro(1L,"Libro 1","ISBN 0000",new Autor());
        libro2 = new Libro(2L,"Libro 2","OSBN 1111",new Autor());

        libroDto = LibroDto.mapToDto(libro2);

        librosLista = Arrays.asList(libro, libro2);
        librosDtoList = LibroDto.mapToDtoList(librosLista);
    }

    @Test
    void getAllTest() throws Exception {
        when(libroService.findAll()).thenReturn(librosLista);
        ResponseEntity<?> ok = libroController.getAll();
        assertEquals(ok, new ResponseEntity<>(librosDtoList, HttpStatus.OK));
    }

    @Test
    void createTest() throws Exception {
        when(libroService.save(any(Libro.class))).thenReturn(libro);
        ResponseEntity<?> ok = libroController.create(libro);
        assertEquals(ok, new ResponseEntity<>(libro, HttpStatus.OK));
    }

    @Test
    void create_dto() throws Exception {
        when(libroService.save(any(Libro.class))).thenReturn(libro2);
        ResponseEntity<?> ok = libroController.create_dto(libroDto);
        assertEquals(ok, new ResponseEntity<>(libroDto, HttpStatus.OK));

    }
}