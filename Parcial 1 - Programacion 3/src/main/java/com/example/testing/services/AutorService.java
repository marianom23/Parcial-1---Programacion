package com.example.testing.services;

import com.example.testing.entities.Autor;
import com.example.testing.entities.Libro;
import com.example.testing.repositories.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository){
        this.autorRepository = autorRepository;
    }

    @Transactional
    public List<Autor> findAll() throws Exception {
        try{
            List<Autor> entities = autorRepository.findAll();
            return entities;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public Autor findById(Long id) throws Exception {
        try{
            Optional<Autor> entityOptional = autorRepository.findById(id);
            return entityOptional.get();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }


    @Transactional
    public Autor save(Autor entity) throws Exception {
        try{
            entity = autorRepository.save(entity);
            return entity;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public Autor update(Long id, Autor entity) throws Exception {
        try{
            if (autorRepository.findById(id).isPresent()) {
                Optional<Autor> entityOptional = autorRepository.findById(id);
                Autor entityUpdate = entityOptional.get();
                entityUpdate = autorRepository.save(entity);
                return entityUpdate;
            } else {
                return null;
            }
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }


    @Transactional
    public boolean deleteById(Long id) throws Exception {
        try{
                autorRepository.deleteAutorById(id);
                return true;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public boolean deleteAutorByNombre(String nombre) throws Exception {
        try{
            boolean bandera = autorRepository.deleteAutorByNombre(nombre);
            return bandera;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

}
