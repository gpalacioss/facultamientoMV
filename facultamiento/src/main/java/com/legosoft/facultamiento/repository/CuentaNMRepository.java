package com.legosoft.facultamiento.repository;

import com.legosoft.facultamiento.models.nuevo.CuentaNM;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface CuentaNMRepository extends Neo4jRepository<CuentaNM, Long> {


    CuentaNM findByNumeroCuenta(String numeroCuenta);

}
