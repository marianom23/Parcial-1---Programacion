package com.example.testing.repositories;

import com.example.testing.entities.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepository extends BaseRepository<Autor, Long> {

    @Query(value = "delete from Autor a where a.nombre = 'nombre'",nativeQuery = true)
    boolean deleteAutorByNombre(@Param("nombre") String nombre);


    @Query(value = "delete from Autor a where a.id = 'id'",nativeQuery = true)
    boolean deleteAutorById(@Param("nombre") Long id);



}
