package com.example.testing.dto;
import com.example.testing.entities.Autor;
import com.example.testing.entities.Libro;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AutorDto {
    private Long id;
    private String nombre;
    private Date fechaNacimiento;

    private static ModelMapper modelMapper = new ModelMapper();

    public static AutorDto mapToDto(Autor autor) throws Exception{
        try {
            AutorDto autorDto = modelMapper.map(autor, AutorDto.class);
            return autorDto;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public static List<AutorDto> mapToDtoList(List<Autor> autores) throws Exception{
        try {
            List<AutorDto> autorDtoList = new ArrayList<>();
            for (Autor a:autores) {
                autorDtoList.add(mapToDto(a));
            }
            return autorDtoList;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

}
