package com.example.testing.entities;

import com.example.testing.dto.LibroDto;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LibroTest {

    @Test
    void mapToEntity() throws Exception {
        LibroDto libroDto = new LibroDto();
        libroDto.setId(1L);
        libroDto.setNombre("Libro Dto 1: ");
        libroDto.setIsbn("ISBN HH1234");

        Libro libro = Libro.mapToEntity(libroDto);
        assertEquals("ISBN HH1234", libro.getIsbn());
    }

    @Test
    void mapToEntityList() throws Exception {
        List<LibroDto> libroDtoList = new ArrayList<>();
        LibroDto lDto1 = LibroDto.builder().id(1L).nombre("Libro Dto 1").isbn("ISBN Libro 1").build();
        LibroDto lDto2 = LibroDto.builder().id(1L).nombre("Libro Dto 2").isbn("ISBN Libro 2").build();

        libroDtoList = Arrays.asList(lDto1, lDto2);

        List<Libro> libros = Libro.mapToEntityList(libroDtoList);

        assertAll("mapToEntityList", ()-> assertEquals("Libro Dto 1", libros.get(0).getNombre()),
                ()-> libros.get(1).getNombre());
    }
}