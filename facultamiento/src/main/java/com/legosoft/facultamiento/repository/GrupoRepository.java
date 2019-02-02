package com.legosoft.facultamiento.repository;

import com.legosoft.facultamiento.models.nuevo.Grupo;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface GrupoRepository extends Neo4jRepository<Grupo, Long> {

    List<Grupo> findAll();

    Grupo findByNombre(String nombre);

    Grupo findByIdGrupo(Long idGrupo);
}
