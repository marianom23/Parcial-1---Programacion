package com.example.testing.builder;

import com.example.testing.dto.LibroDto;
import com.example.testing.entities.Autor;
import com.example.testing.entities.Libro;
import lombok.Builder;

@Builder
public class LibroBuilder {

    @Builder.Default
    private Long id = 1L;

    @Builder.Default
    private String nombre = "Nombre por defecto";

    @Builder.Default
    private String isbn = "ISBN 0000";

    public Libro toLibro(){
        return new Libro(id,
                nombre,
                isbn,
                new Autor());
    }

}