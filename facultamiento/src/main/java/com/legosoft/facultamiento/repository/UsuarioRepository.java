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
import java.util.Optional;

@Repository
public interface UsuarioRepository extends Neo4jRepository<Usuario, Long>{
	
	Collection<Usuario> findAll();
	
	List<Usuario> findByNombre(String nombre, @Depth int dept);
	
	
	@Query("MATCH (u:Usuario)-[rp:HAS_PERFIL]-> (p:Perfil)-[rf:HAS_FACULTAD]-> (f:Facultad) where u.nombre={nombre} RETURN u,rp,p,rf,f")
	List<Usuario> getInfoUsuario(@Param("nombre") String nombre);


	@Override
	Optional<Usuario> findById(Long id);

	@Query("MATCH (u:Usuario)-[:USUARIO_HAS_CUENTA]->(c:CuentaNM) where c.numeroCuenta = {numeroCuenta} RETURN u ")
	List<Usuario> findusuariosByNumeroCuenta(@Param("numeroCuenta") String numeroCuenta);


}