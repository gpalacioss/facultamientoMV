package com.legosoft.facultamiento.repository;


import com.legosoft.facultamiento.models.nuevo.PermisoCuenta;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PermisoCuentaRepository extends Neo4jRepository<PermisoCuenta, Long> {

    @Override
    Optional<PermisoCuenta> findById(Long aLong);

    @Query("MATCH  (u:Usuario)-[r:USUARIO_HAS_CUENTA_PERMISO]->(upc:UsuarioPermisoCuenta) WHERE u.nombre = {nombreUsuario} RETURN r")
    PermisoCuenta findByPermisoAndCuenta(@Param("nombreUsuario") String nombreUsuario);


}
