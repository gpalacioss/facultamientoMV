package com.legosoft.facultamiento.repository;


import java.util.Collection;
import java.util.List;
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
	
	List<Usuario> findByNombre(String nombre);
	
	
	@Query("MATCH (u:Usuario)-[rp:HAS_PERFIL]-> (p:Perfil)-[rf:HAS_FACULTAD]-> (f:Facultad) where u.nombre={nombre} RETURN u,rp,p,rf,f")
	List<Usuario> getInfoUsuario(@Param("nombre") String nombre);


	@Override
	Optional<Usuario> findById(Long id);

	@Query("MATCH (u:Usuario)-[:USUARIO_HAS_CUENTA]->(c:CuentaNM) where c.numeroCuenta = {numeroCuenta} RETURN u ")
	List<Usuario> findusuariosByNumeroCuenta(@Param("numeroCuenta") String numeroCuenta);


	@Query("MATCH query=(u:Usuario)-[:HAS_PERFIL_NM]-(p:PerfilNM)-[:HAS_ROL]->(r:Rol)-[:HAS_FACULTAD_ROL]-(pr:Permiso) where u.nombre = {nombreUsuario} AND NOT (u)-[:DENIED]->(pr)  OPTIONAL MATCH sq1=(u)-[:PERMISO_AGREGADO]->(ps:Permiso) RETURN query, sq1")
	Usuario getUsuarioAndPermisos(@Param("nombreUsuario") String nombreUsuario);



}