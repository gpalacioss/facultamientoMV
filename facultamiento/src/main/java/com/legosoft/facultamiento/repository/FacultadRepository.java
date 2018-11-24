package com.legosoft.facultamiento.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.legosoft.facultamiento.models.old.Facultad;

import java.util.List;

public interface FacultadRepository extends Neo4jRepository<Facultad, Long>{


    List<Facultad> findAll();

}
