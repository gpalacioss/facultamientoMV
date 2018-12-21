package com.legosoft.facultamiento.repository;

import com.legosoft.facultamiento.models.nuevo.CuentaNM;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CuentaNMRepository extends Neo4jRepository<CuentaNM, Long> {


    CuentaNM findByNumeroCuenta(String numeroCuenta);

    @Query("MATCH(c:CuentaNM) WHERE c.numeroCuenta IN {listNumeroCuenta} RETURN c")
    List<CuentaNM> findCuentaByListParameter(@Param("listNumeroCuenta") String listNumeroCuenta);

}
