package com.example.testing.controllers;

import com.example.testing.dto.AutorDto;
import com.example.testing.entities.Autor;
import com.example.testing.entities.Libro;
import com.example.testing.repositories.AutorRepository;
import com.example.testing.services.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(value = "*")
@RequestMapping(path = "api/v1/autores")
public class AutorController {

    @Autowired
    private AutorService autorService;

    Optional<Autor> autor;
    AutorDto autorDto;

    private AutorRepository autorRepository;
    public AutorController(AutorRepository autorRepositoryMock) {
        this.autorRepository = autorRepositoryMock;
    }


    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() throws Exception{
        try{
            List<Autor> autores = this.autorService.findAll();
            List<AutorDto> autorDtoList = AutorDto.mapToDtoList(autores);
            return ResponseEntity.status(HttpStatus.OK).body(autorDtoList);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error");
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Autor autor) throws Exception{
        try{
            return ResponseEntity.status(HttpStatus.OK).body(autorService.save(autor));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error");
        }
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<AutorDto> getOne(@PathVariable("id") Long id) {
        try{
            autor = Optional.of(new Autor());
            autorDto = new AutorDto();

            autor = Optional.ofNullable(autorService.findById(id));

            if(autor.isPresent()) {
                autorDto.setNombre(autor.get().getNombre());
                autorDto.setId(autor.get().getId());
            }
            return ResponseEntity.ok(autorDto);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}
