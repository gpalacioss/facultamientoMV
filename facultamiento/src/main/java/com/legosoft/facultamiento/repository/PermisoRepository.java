package com.legosoft.facultamiento.repository;

import com.legosoft.facultamiento.models.nuevo.Permiso;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PermisoRepository extends Neo4jRepository<Permiso, Long> {


    Permiso findByIdPermiso(Long idPermiso);

    Permiso findByNombre(String nombrePermiso);

    List<Permiso> findAll();

    @Query("MATCH(p:Permiso)-[r:HAS_PERMISO_CUENTA]->(c:CuentaNM) RETURN p, r, c")
    List<Permiso> findPermisoConCuentas();

    @Query("MATCH (p:Permiso)<-[:HAS_FACULTAD_ROL]-(r:Rol)<-[:HAS_ROL]-(pr:PerfilNM) where pr.nombre = {nombrePerfil} RETURN p")
    List<Permiso> findPermisosByNombrePerfil(@Param("nombrePerfil") String nombrePerfil);
}
