package com.example.testing.services;

import com.example.testing.entities.Autor;
import com.example.testing.repositories.AutorRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.BooleanSupplier;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(value = {MockitoExtension.class})
//@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
class AutorServiceTest {

    @InjectMocks
    private AutorService autorService;

    @Mock
    private AutorRepository autorRepository;

    @Autowired
    private Autor autor;

    @BeforeEach
    @Test
    public void setUp() throws Exception {
        List<Autor> mockListAutor = mock(List.class);
        mockListAutor.add(new Autor(1L, "Jose Pasini", "josepasini.16@gmail.com"));
        mockListAutor.add(new Autor(2L, "Pedro Juan", "carloscabello.22@gmail.com"));
        mockListAutor.add(new Autor(3L, "Pepe Lui", "pepe_lui.33@gmail.com"));
        verify(mockListAutor, times(3)).add(argumentCaptor.capture());

        autor = new Autor(1L, "PEPE LUI", "pepe@gmail.com");

        List<Autor> autoresLista = argumentCaptor.getAllValues();

        assertEquals("Jose Pasini", autoresLista.get(0).getNombre());

    }

    @Captor
    private ArgumentCaptor<Autor> argumentCaptor;

    @Test
    void capterTest(){
        List<Autor> mockListAutor = mock(List.class);
        mockListAutor.add(new Autor(1L, "Jose Pasini", "josepasini.16@gmail.com"));
        mockListAutor.add(new Autor(2L, "Pedro Juan", "carloscabello.22@gmail.com"));
        mockListAutor.add(new Autor(3L, "Pepe Lui", "pepe_lui.33@gmail.com"));

        verify(mockListAutor, times(3)).add(argumentCaptor.capture());

        List<Autor> autoresLista = argumentCaptor.getAllValues();

        assertEquals(1L, autoresLista.get(0).getId());
        assertEquals("Jose Pasini", autoresLista.get(0).getNombre());

        assertEquals(2L, autoresLista.get(1).getId());
        assertEquals("Pedro Juan", autoresLista.get(1).getNombre());

        assertEquals(3L, autoresLista.get(2).getId());
        assertEquals("Pepe Lui", autoresLista.get(2).getNombre());
    }

    @Test
    void testFindById() throws Exception {
        Autor autor = getAutor(1);
        when(autorRepository.findById(1L)).thenReturn(Optional.of(autor));
        assertEquals("Jose Pasini", autorService.findById(1L).getNombre());
        assertEquals(1, autorService.findById(1L).getId());

        // assertAll, como primer parámetro la cabecera del Método, y después funciones lambdas para todos los assert.
        // esta anotación reemplazaría a las dos de arriba
        assertAll("testFindById", ()-> assertEquals("Jose Pasini", autorService.findById(1L).getNombre()),
                ()-> assertEquals(1, autorService.findById(1L).getId()),
                ()-> assertEquals("josepasini.17@gmail.com", autorService.findById(1L).getEmail().toLowerCase()) );
    }

    @Test
    void testFindAll() throws Exception {
        List<Autor> autores = saveListAutor();
        List<Autor> autores_2 = autorService.findAll();



        System.out.println("Size: " + autores_2.size());
        assertAll("testFindAll", () -> assertEquals("Jose Pasini", autores.get(0).getNombre()));

    }

    @Test
    void testFindAll_mock() throws Exception {
        List<Autor> autores = saveListAutor();
        when(autorRepository.findAll()).thenReturn(autores);
        List<Autor> autores_2 = autorService.findAll();
        assertNotNull(autores_2);
        assertAll("testFindAll", () -> assertEquals("Jose Pasini", autores.get(0).getNombre()));

    }


    private List<Autor> saveListAutor() throws Exception {

        List<Autor> mockListAutor = mock(List.class);
        mockListAutor.add(new Autor(1L, "Jose Pasini", "josepasini.16@gmail.com"));
        mockListAutor.add(new Autor(2L, "Pedro Juan", "carloscabello.22@gmail.com"));
        mockListAutor.add(new Autor(3L, "Pepe Lui", "pepe_lui.33@gmail.com"));

        verify(mockListAutor, times(3)).add(argumentCaptor.capture());

        List<Autor> autoresLista = argumentCaptor.getAllValues();
        return autoresLista;
    }

    private Autor getAutor(int a){
        if (a == 1) {
            Autor autor = Autor.builder()
                    .id(1L)
                    .nombre("Jose Pasini")
                    .email("josepasini.17@gmail.com")
                    .build();
            return autor;
        } else {
            return Autor.builder().id(3L).nombre("Undefined").build();
        }
    }

    @Test
    void saveTest() throws Exception {
        when(this.autorRepository.save(any(Autor.class))).thenReturn(autor);
        assertNotNull(this.autorService.save(new Autor()));
    }

    @Test
    void updateTest() throws Exception {
        when(autorRepository.getById(autor.getId())).thenReturn(autor);
        when(autorRepository.save(autor)).thenReturn(autor);
        assertNotNull(autorService.update(1L, autor));

    }

    @Test
    void deleteAutorByNombreTest() throws Exception {
        System.out.println("AUTOR: " + autor.getNombre());
        when(this.autorRepository.deleteAutorByNombre(any(String.class))).thenReturn(true);
        assertTrue(autorService.deleteAutorByNombre("nombre"));
    }

    @Test
    void deleteAutorByIdTest() throws Exception{
        System.out.println("AUTOR: " + autor.getNombre());
        when(this.autorRepository.deleteAutorById(any(Long.class))).thenReturn(true);
        assertTrue(autorService.deleteById(1L));
    }

}