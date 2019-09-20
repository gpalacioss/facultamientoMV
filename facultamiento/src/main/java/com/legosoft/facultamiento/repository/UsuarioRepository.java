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

/**
 * Todo: Repositorio usuario que hereda de Neo4jRepository, esta cinterfaz sirve para el acceso a datos de neo4j enlazado con spring data, el cual configura querys prehechos pero tambien se pueden hacer personalizados con la anotacion @Query
 */
public interface UsuarioRepository extends Neo4jRepository<Usuario, Long>{
	
	List<Usuario> findAll();
	
	List<Usuario> findByNombre(String nombre);
	
	Usuario findByNombreUsuario(String username);

	@Query("MATCH (u:User)-[rp:HAS_PERFIL]-> (p:Perfil)-[rf:HAS_FACULTAD]-> (f:Facultad) where u.nombre={nombre} RETURN u,rp,p,rf,f")
	List<Usuario> getInfoUsuario(@Param("nombre") String nombre);


	@Override
	Optional<Usuario> findById(Long id);

	@Query("MATCH (u:User)-[:HAS_ACCOUNT]->(c:Account) where c.numeroCuenta = {numeroCuenta} RETURN u ")
	List<Usuario> findusuariosByNumeroCuenta(@Param("numeroCuenta") String numeroCuenta);


	@Query("MATCH query=(u:User)-[:HAS_PROFILE]-(p:Profile)-[:HAS_ROLE]->(r:Role)-[:HAS_PERMIT]-(pr:Permit) where u.nombre = {nombreUsuario} AND NOT (u)-[:DENIED]->(pr)  OPTIONAL MATCH sq1=(u)-[:ADDED_PERMIT]->(ps:Permit) RETURN query, sq1")
	Usuario getUsuarioAndPermisos(@Param("nombreUsuario") String nombreUsuario);


	@Query("MATCH query=(admin:User)-[:MEMBER_OF]->(g:Group)-[:ALLOW]-(c:Company) where admin.nombre = {nombreAdministrador} optional MATCH sq=(g)-[:ALLOWED_DO_NOT_INHERIT]->(csh:Company)  optional MATCH sq2=(c)<-[:CHILD_OF]-(ch:Company)  WHERE NOT (g)-[:DENIED]->(ch)  RETURN query, sq, sq2")
	Usuario empresasbyAdministradorGraph(@Param("nombreAdministrador") String nombreAdministrador);


	@Query("MATCH query=(admin:User)-[:MEMBER_OF]->(g:Group)-[:ALLOW]-(c:Company)<-[:WORKS_FOR]-(em:User) where admin.nombre = {nombreAdministrador} optional MATCH subquery=(gr:Group)-[r:ALLOWED_DO_NOT_INHERIT]->(csh:Company)<-[:WORKS_FOR]-(emcsh:User) WHERE(admin)-[:MEMBER_OF]->(gr)  optional MATCH subquery2=(c)<-[:CHILD_OF]-(ch:Company)<-[:WORKS_FOR]-(emch:User) WHERE NOT (g)-[:DENIED]->(ch)  RETURN query, subquery, subquery2")
	List<Usuario> empleadosAndEmpresasByAdministradorGraph(@Param("nombreAdministrador") String nombreAdministrador);


	@Query("MATCH query=(admin:User)-[:MEMBER_OF]->(g:Group)-[:ALLOW]-(c:Company)-[:HAS_ACCOUNT]->(cn:Account) where admin.nombre = {nombreAdministrador}  optional MATCH subquery=(gr:Group)-[r:ALLOWED_DO_NOT_INHERIT]->(csh:Company)-[:HAS_ACCOUNT]->(cncsh:Account) WHERE(admin)-[:MEMBER_OF]->(gr)  optional MATCH subquery2=(c)<-[:CHILD_OF]-(ch:Company)-[:HAS_ACCOUNT]->(cnch:Account) WHERE NOT (g)-[:DENIED]->(ch)  RETURN query, subquery, subquery2")
	Usuario cuentasEmpresasByAdministrador(@Param("nombreAdministrador") String nombreAdministrador);


	@Query("MATCH query=(u:User)-[:HAS_ACCOUNT_PERMIT]->(upc:UserPermitAccount)<-[:HAS_ACCOUNT_PERMIT]-(pcm:Permit) where u.nombre = {nombreUsuario} AND NOT (u)-[:DENIED]->(pcm)  OPTIONAL MATCH sq1=(u)-[:ADDED_PERMIT]->(ps:Permit) OPTIONAL MATCH sq2=(upc)<-[:HAS_ACCOUNT_PERMIT]-(cm:Account) OPTIONAL MATCH sq3=(u)-[:HAS_ACCOUNT]->(cm)  RETURN query, sq1, sq2, sq3")
	Usuario permisosCuentaMontoByUsuario(@Param("nombreUsuario") String nombreUsuario);


	@Query("MATCH query=(u:User)-[:HAS_PROFILE]-(p:Profile)-[:HAS_ROLE]->(r:Role)-[:HAS_PERMIT]-(pr:Permit) where u.nombre = {nombreUsuario} AND NOT (u)-[:DENIED]->(pr)  OPTIONAL MATCH sq1=(u)-[:ADDED_PERMIT]->(ps:Permit) OPTIONAL MATCH sq2=(u)-[:HAS_ACCOUNT_PERMIT]->(upc:UserPermitAccount)<-[:HAS_ACCOUNT_PERMIT]-(cm:Account) OPTIONAL MATCH sq3=(upc)<-[:HAS_ACCOUNT_PERMIT]-(pcm:Permit)  RETURN query, sq1, sq2, sq3")
	Usuario permisoCuentaMontoAndSimplebyUsuario(@Param("nombreUsuario") String nombreUsuario);

	@Query("MATCH(u:User)-[r:ADDED_PERMIT]->(p:Permit) where u.nombre = {nombreUsuario} and p.nombre = {nombrePermiso} DELETE r")
	void deletePermisosAgregados(@Param("nombreUsuario") String nombreUsuario, @Param("nombrePermiso") String nombrePermiso);


}
