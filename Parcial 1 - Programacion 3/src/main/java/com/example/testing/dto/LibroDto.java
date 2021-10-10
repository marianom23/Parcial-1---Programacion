package com.example.testing.dto;


import com.example.testing.entities.Autor;
import com.example.testing.entities.Libro;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LibroDto {
    private Long id;
    private String nombre;
    private String isbn;
    @JsonIgnoreProperties(value = "autor")
    private Autor autor;


    private static ModelMapper modelMapper = new ModelMapper();


    public LibroDto(Long id, String nombre, String isbn){
        this.id = id;
        this.nombre = nombre;
        this.isbn = isbn;
    }

    public static LibroDto mapToDto(Libro libro) throws Exception{
        try {
            LibroDto libroDto = modelMapper.map(libro, LibroDto.class);
            return libroDto;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public static List<LibroDto> mapToDtoList(List<Libro> libros) throws Exception{
        try {
            List<LibroDto> libroDtoList = new ArrayList<>();
            for (Libro l:libros) {
                libroDtoList.add(mapToDto(l));
            }
            return libroDtoList;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

}
