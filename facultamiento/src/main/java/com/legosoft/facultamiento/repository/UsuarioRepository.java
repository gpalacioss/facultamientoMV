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


	@Query("MATCH query=(admin:Usuario)-[:MEMBER_OF]->(g:Grupo)-[:ALLOW]-(c:Compania) where admin.nombre = {nombreAdministrador} optional MATCH sq=(g)-[:ALLOWED_DO_NOT_INHERIT]->(csh:Compania)  optional MATCH sq2=(c)<-[:CHILD_OF]-(ch:Compania)  WHERE NOT (g)-[:DENIED]->(ch)  RETURN query, sq, sq2")
	Usuario empresasbyAdministradorGraph(@Param("nombreAdministrador") String nombreAdministrador);


	@Query("MATCH query=(admin:Usuario)-[:MEMBER_OF]->(g:Grupo)-[:ALLOW]-(c:Compania)<-[:TRABAJA_EN]-(em:Usuario) where admin.nombre = {nombreAdministrador} optional MATCH subquery=(gr:Grupo)-[r:ALLOWED_DO_NOT_INHERIT]->(csh:Compania)<-[:TRABAJA_EN]-(emcsh:Usuario) WHERE(admin)-[:MEMBER_OF]->(gr)  optional MATCH subquery2=(c)<-[:CHILD_OF]-(ch:Compania)<-[:TRABAJA_EN]-(emch:Usuario) WHERE NOT (g)-[:DENIED]->(ch)  RETURN query, subquery, subquery2")
	Usuario empleadosAndEmpresasByAdministradorGraph(@Param("nombreAdministrador") String nombreAdministrador);


	@Query("MATCH query=(admin:Usuario)-[:MEMBER_OF]->(g:Grupo)-[:ALLOW]-(c:Compania)-[:COMPANIA_HAS_CUENTA]->(cn:CuentaNM) where admin.nombre = {nombreAdministrador}  optional MATCH subquery=(gr:Grupo)-[r:ALLOWED_DO_NOT_INHERIT]->(csh:Compania)-[:COMPANIA_HAS_CUENTA]->(cncsh:CuentaNM) WHERE(admin)-[:MEMBER_OF]->(gr)  optional MATCH subquery2=(c)<-[:CHILD_OF]-(ch:Compania)-[:COMPANIA_HAS_CUENTA]->(cnch:CuentaNM) WHERE NOT (g)-[:DENIED]->(ch)  RETURN query, subquery, subquery2")
	Usuario cuentasEmpresasByAdministrador(@Param("nombreAdministrador") String nombreAdministrador);


	@Query("MATCH query=(u:Usuario)-[:USUARIO_HAS_CUENTA_PERMISO]->(upc:UsuarioPermisoCuenta)<-[:USUARIO_HAS_CUENTA_PERMISO]-(pcm:Permiso) where u.nombre = {nombreUsuario} AND NOT (u)-[:DENIED]->(pcm)  OPTIONAL MATCH sq1=(u)-[:PERMISO_AGREGADO]->(ps:Permiso) OPTIONAL MATCH sq2=(upc)<-[:USUARIO_HAS_CUENTA_PERMISO]-(cm:CuentaNM)  RETURN query, sq1, sq2")
	Usuario permisosCuentaMontoByUsuario(@Param("nombreUsuario") String nombreUsuario);


	@Query("MATCH query=(u:Usuario)-[:HAS_PERFIL_NM]-(p:PerfilNM)-[:HAS_ROL]->(r:Rol)-[:HAS_FACULTAD_ROL]-(pr:Permiso) where u.nombre = {nombreUsuario} AND NOT (u)-[:DENIED]->(pr)  OPTIONAL MATCH sq1=(u)-[:PERMISO_AGREGADO]->(ps:Permiso) OPTIONAL MATCH sq2=(u)-[:USUARIO_HAS_CUENTA_PERMISO]->(upc:UsuarioPermisoCuenta)<-[:USUARIO_HAS_CUENTA_PERMISO]-(cm:CuentaNM) OPTIONAL MATCH sq3=(upc)<-[:USUARIO_HAS_CUENTA_PERMISO]-(pcm:Permiso)  RETURN query, sq1, sq2, sq3")
	Usuario permisoCuentaMontoAndSimplebyUsuario(@Param("nombreUsuario") String nombreUsuario);


}