package com.example.testing;


import com.example.testing.dto.AutorDto;
import com.example.testing.dto.LibroDto;
import com.example.testing.entities.Autor;
import com.example.testing.entities.Libro;
import com.example.testing.repositories.AutorRepository;
import com.example.testing.repositories.LibroRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestingApplicationTests {

    @Autowired
    AutorRepository autorRepository;

    @Autowired
    LibroRepository libroRepository;

    @Test
    @Order(1)
    public void testCreate_autor(){
        Autor autor = Autor.builder()
                .id(1L)
                .nombre("Horacio Quiroga")
                .email("hquiroga@gmail.com")
                .build();
        autorRepository.save(autor);
        Assertions.assertNotNull(autorRepository.findById(1L).get());
    }

    @Test
    @Order(2)
    public void testReadAll_autor(){
        List<Autor> autores= autorRepository.findAll();
        org.assertj.core.api.Assertions.assertThat(autores).size().isGreaterThan(0);
    }

    @Test
    @Order(3)
    public void testReadSingle_autor(){
        Autor autor = autorRepository.findById(1L).get();
        Assertions.assertEquals("Horacio Quiroga", autor.getNombre());
    }

    @Test
    @Order(4)
    public void testUpdate_autor(){
        Autor autor = autorRepository.findById(1L).get();
        autor.setNombre("Mario Borges");
        autorRepository.save(autor);
        Assertions.assertNotEquals("Horacio Quiroga", autorRepository.findById(1L).get().getNombre());
    }

    @Test
    @Order(10)
    public void testDelete_autor(){
        Autor autor = new Autor();
        autor.setId(1L);
        autor.setNombre("Isabel Allende");
        autorRepository.save(autor);
        autorRepository.deleteById(1L);
        org.assertj.core.api.Assertions.assertThat(autorRepository.existsById(1L)).isFalse();
    }

    @Test
    @Order(5)
    public void test_libro(){
        Libro libro = new Libro();
        libro.setId(1L);
        libro.setNombre("Cuentos de la selva");
        libro.setIsbn("HH1234");
        libro.setAutor(autorRepository.findById(1L).get());
        System.out.println(libro);
        libroRepository.save(libro);
        System.out.println(libro);
        Assertions.assertNotNull(libroRepository.findById(1L).get());
    }

    @Test
    @Order(6)
    public void readAll_libro(){
        List<Libro> libros = libroRepository.findAll();
        org.assertj.core.api.Assertions.assertThat(libros).size().isGreaterThan(0);
    }

    @Test@Order(7)
    public void readOne_libro(){
        Libro libro = libroRepository.findById(1L).get();
        String libro_test = "Cuentos de la selva";
        Assertions.assertEquals(libro_test, libro.getNombre());
    }

    @Test
    @Order(8)
    public void update_libro(){
        Libro libro = this.libroRepository.findById(1L).get();
        libro.setNombre("Narnia");
        this.libroRepository.save(libro);
        Assertions.assertNotEquals("Cuentos de la selva", this.libroRepository.findById(1L).get().getNombre());
    }

    @Test
    @Order(9)
    public void delete_libro(){
        this.libroRepository.delete(libroRepository.getById(1L));
        Assertions.assertNotNull(libroRepository.getById(1L));
    }

    @Test
    @Order(11)
    public void test_mapeo_entidad_autor() throws Exception{
        List<AutorDto> autoresDto= new ArrayList<>();
        List<Autor> autores= Autor.mapToEntityList(autoresDto);
        autores.add(new Autor());
        org.assertj.core.api.Assertions.assertThat(autores).size().isGreaterThan(0);
    }

    @Test
    @Order(12)
    public void test_mapeo_lista_entidad() throws Exception{
        List<AutorDto> autoresDto= new ArrayList<>();
        List<Autor> autores = Autor.mapToEntityList(autoresDto);
        autores.add(new Autor());
        org.assertj.core.api.Assertions.assertThat(autores).size().isGreaterThan(0);
    }
    @Test
    @Order(13)
    public void test_mapeo_entidad_libro()throws Exception{
        LibroDto libroDto = new LibroDto();
        Libro libro = Libro.mapToEntity(libroDto);
        libroDto.setId(2L);
        libroDto.setNombre("El señor de los anillos");
        Assertions.assertEquals("El señor de los anillos", libroDto.getNombre());

    }

    @Test
    @Order(14)
    public void test_mapeo_lista_DTO() throws Exception{
        List<LibroDto> librosDto = new ArrayList<>();
        List<Libro> libros = Libro.mapToEntityList(librosDto);
        libros.add(new Libro());
        org.assertj.core.api.Assertions.assertThat(libros).size().isGreaterThan(0);
    }

}