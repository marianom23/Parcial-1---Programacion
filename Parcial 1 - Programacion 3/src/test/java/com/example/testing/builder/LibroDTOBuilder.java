package com.example.testing.builder;

import com.example.testing.dto.LibroDto;
import lombok.Builder;

@Builder
public class LibroDTOBuilder {

    @Builder.Default
    private Long id = 1L;
    @Builder.Default
    private String nombre = "Nombre por defecto";

    @Builder.Default
    private String isbn = "ISBN 0000";

    public LibroDto toLibroDTO(){
        return new LibroDto(id,
                nombre,
                isbn);
    }

}
