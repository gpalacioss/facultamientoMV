package com.legosoft.facultamiento.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.legosoft.facultamiento.models.nuevo.PerfilNM;

import java.util.List;

public interface PerfilNMRepository extends Neo4jRepository<PerfilNM, Long>{

    List<PerfilNM> findAll();


}
