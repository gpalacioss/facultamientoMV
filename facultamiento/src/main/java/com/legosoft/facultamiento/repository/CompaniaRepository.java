package com.legosoft.facultamiento.repository;

import com.legosoft.facultamiento.models.nuevo.Compania;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface CompaniaRepository extends Neo4jRepository<Compania, Long> {
}
