package com.legosoft.facultamiento.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.legosoft.facultamiento.models.nuevo.PerfilNM;

import java.util.List;
import java.util.Optional;

public interface PerfilNMRepository extends Neo4jRepository<PerfilNM, Long>{

    List<PerfilNM> findAll();

    @Override
    Optional<PerfilNM> findById(Long aLong);

    Optional<PerfilNM> findByNombre(String nombrePerfil);
}
