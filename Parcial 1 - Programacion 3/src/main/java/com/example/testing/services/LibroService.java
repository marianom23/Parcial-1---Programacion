package com.example.testing.services;

import com.example.testing.entities.Libro;
import com.example.testing.repositories.BaseRepository;
import com.example.testing.repositories.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    @Transactional
    public List<Libro> findAll() throws Exception {
        try{
            List<Libro> entities = libroRepository.findAll();
            return entities;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public Libro findById(Long id) throws Exception {
        try{
            Optional<Libro> entityOptional = libroRepository.findById(id);
            return entityOptional.get();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public Libro save(Libro entity) throws Exception {
        try{
            entity = libroRepository.save(entity);
            return entity;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public Libro update(Long id, Libro entity) throws Exception {
        try{
            if (libroRepository.findById(id).isPresent()) {
                Optional<Libro> entityOptional = libroRepository.findById(id);
                Libro entityUpdate = entityOptional.get();
                entityUpdate = libroRepository.save(entity);
                return entityUpdate;
            } else {
                return null;
            }
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public boolean delete(Long id) throws Exception {
        try{
            if (libroRepository.existsById(id)){
                libroRepository.deleteById(id);
                return true;
            } else {
                throw new Exception();
            }
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

}
