package com.legosoft.facultamiento.repository;

import com.legosoft.facultamiento.models.nuevo.Compania;
import org.neo4j.cypher.internal.frontend.v2_3.ast.functions.Str;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface CompaniaRepository extends Neo4jRepository<Compania, Long> {

    Compania findCompaniaByNombreCompania(String nombreCompania);

    List<Compania> findAll();
}
