package com.legosoft.facultamiento.repository;

import com.legosoft.facultamiento.models.nuevo.Grupo;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface GrupoRepository extends Neo4jRepository<Grupo, Long> {
}
