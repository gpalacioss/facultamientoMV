package com.legosoft.facultamiento.repository;

import com.legosoft.facultamiento.models.nuevo.UsuarioPermisoCuenta;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsuarioPermisoCuentaRepository extends Neo4jRepository<UsuarioPermisoCuenta, Long> {

    List<UsuarioPermisoCuenta> findAll();

    @Query("MATCH (u:Usuario)-[USUARIO_HAS_CUENTA_PERMISO]->(upc:UsuarioPermisoCuenta)<-[:USUARIO_HAS_CUENTA_PERMISO]-(p:Permiso), (upc)<-[:USUARIO_HAS_CUENTA_PERMISO]-(c:CuentaNM)  where u.nombre = {nombreUsuario} and c.numeroCuenta = {numeroCuenta} and p.nombre = {nombrePermiso} RETURN upc ")
    UsuarioPermisoCuenta findUsuarioPermisoCuentaByusuarioAndPermisoAndCuenta(@Param("nombreUsuario") String nombreUsuario, @Param("numeroCuenta") String numeroCuenta, @Param("nombrePermiso") String nombrePermiso);


    @Query("MATCH (c:CuentaNM)-[rc:USUARIO_HAS_CUENTA_PERMISO]->(upc:UsuarioPermisoCuenta)<-[rp:USUARIO_HAS_CUENTA_PERMISO]-(p:Permiso) where c.numeroCuenta = {numeroCuenta} and p.nombre = {nombrePermiso} RETURN upc,rc, c,rp, p")
    UsuarioPermisoCuenta findUsuarioPermisoCuentaByPermisoAndCuenta( @Param("numeroCuenta") String numeroCuenta, @Param("nombrePermiso") String nombrePermiso);

    @Override
    Optional<UsuarioPermisoCuenta> findById(Long aLong);
}
