package com.legosoft.facultamiento.repository;

import com.legosoft.facultamiento.models.nuevo.Compania;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface CompaniaRepository extends Neo4jRepository<Compania, Long> {

    Compania findCompaniaByNombreCompania(String nombreCompania);

    List<Compania> findAll();

    Compania findByIdCompania(Long idCompania);
}
