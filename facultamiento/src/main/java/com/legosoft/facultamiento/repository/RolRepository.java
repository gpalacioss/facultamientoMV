package com.legosoft.facultamiento.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.legosoft.facultamiento.models.nuevo.Rol;

import java.util.List;

public interface RolRepository extends Neo4jRepository<Rol, Long>{


    List<Rol> findAll();


}
