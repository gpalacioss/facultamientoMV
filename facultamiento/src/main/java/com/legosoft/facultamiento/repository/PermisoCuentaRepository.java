package com.legosoft.facultamiento.repository;


import com.legosoft.facultamiento.models.nuevo.PermisoCuenta;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PermisoCuentaRepository extends Neo4jRepository<PermisoCuenta, Long> {

    @Override
    Optional<PermisoCuenta> findById(Long aLong);

    @Query("MATCH  (p:Permiso)-[r:HAS_PERMISO_CUENTA]->(c:CuentaNM) WHERE p.nombre = {nombrePermiso} and c.numeroCuenta = {numeroCuenta} RETURN r")
    PermisoCuenta findByPermisoAndCuenta(@Param("nombrePermiso") String nombrePermiso, @Param("numeroCuenta") String numeroCuenta);


}
