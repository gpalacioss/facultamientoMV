package com.legosoft.facultamiento.repository;

import com.legosoft.facultamiento.models.nuevo.PerfilNM;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.legosoft.facultamiento.models.old.Perfil;

import java.lang.String;
import java.util.List;

public interface PerfilRepository extends Neo4jRepository<Perfil, Long>{
	
	@Query("MATCH(u:Usuario)-[:HAS_PERFIL]->(p:Perfil{nombre:'Administrador PMO2'})-[:HAS_FACULTAD]->(f:Facultad) WITH u,p,f RETURN u, p, f, ")
	public Perfil buscaPerfilAndUsario();

	
	List<Perfil> findByNombre(String nombre);
	
	List<Perfil> findAll();
}
