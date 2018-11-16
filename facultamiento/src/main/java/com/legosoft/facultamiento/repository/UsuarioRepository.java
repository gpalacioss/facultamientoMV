package com.legosoft.facultamiento.repository;


import java.util.Collection;
import java.util.List;

import org.neo4j.ogm.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.annotation.Depth;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.legosoft.facultamiento.models.old.Usuario;

import java.lang.String;

@Repository
public interface UsuarioRepository extends Neo4jRepository<Usuario, Long>{
	
	Collection<Usuario> findAll();
	
	List<Usuario> findByNombre(String nombre, @Depth int dept);
	
	
	@Query("MATCH (u:Usuario)-[:HAS_PERFIL]-> (p:Perfil)-[:HAS_FACULTAD]-> (f:Facultad) where u.nombre={nombre} RETURN u,p,f")
	@Depth(value = 5)
	List<Usuario> getInfoUsuario(@Param("nombre") String nombre);
	
}