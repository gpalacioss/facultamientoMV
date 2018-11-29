package com.legosoft.facultamiento.repository;

import com.legosoft.facultamiento.models.nuevo.Permiso;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface PermisoRepository extends Neo4jRepository<Permiso, Long> {


    Permiso findByIdPermiso(Long idPermiso);

    Permiso findByNombre(String nombrePermiso);
}
