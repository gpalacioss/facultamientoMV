package com.legosoft.facultamiento.repository;

import com.legosoft.facultamiento.models.nuevo.FacultadNM;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface FacultadNMRepository extends Neo4jRepository<FacultadNM, Long> {


    FacultadNM findByIdFacultad(Long idFacultad);
}
