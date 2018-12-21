package com.legosoft.facultamiento.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sun.tools.javac.jvm.ByteCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.legosoft.facultamiento.models.old.Perfil;
import com.legosoft.facultamiento.models.old.Usuario;
import com.legosoft.facultamiento.repository.PerfilRepository;
import com.legosoft.facultamiento.repository.UsuarioRepository;
import com.legosoft.facultamiento.service.UsuarioService;

@Service("usuarioService")
public class UsuarioServiceImpl implements UsuarioService{
	
	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private PerfilRepository perfilRepository;
	
	public List<Usuario> getAllUsuarios(){
		List<Usuario> result = (List<Usuario>) repository.findAll();
		return result;
	}

	public List<Usuario> getInfoUsuario(String nombre){
		List<Usuario> result = repository.getInfoUsuario(nombre);
		return result;
	}
	
	public Perfil getPerfil() {
		Perfil result =  perfilRepository.buscaPerfilAndUsario();
		return result;
	}
	
	public List<Usuario> findUsuarioBynombre(String nombre) {
		List<Usuario> result = repository.findByNombre(nombre);
		return result;
	}
	
	public void save(Usuario usuario) {
		repository.save(usuario);
	}

	public Optional<Usuario> findUsuarioById(Long id){
		return repository.findById(id);
	}

	public Usuario saveOrUpdate(Usuario usuario){
		return repository.save(usuario);
	}

	public List<Usuario> findUsuariosByNumeroCuenta(String numeroCuenta){
		return repository.findusuariosByNumeroCuenta(numeroCuenta);
	}


	public String getUsuarioAndPermisosGraph(String nombreUsuario){

		ArrayNode arrayNode = (new ObjectMapper()).createObjectNode().putArray("nodes");
		ArrayNode arrayEdges = (new ObjectMapper()).createObjectNode().putArray("edges");

		Usuario repoUsuario = repository.getUsuarioAndPermisos(nombreUsuario);

		arrayNode.add(generaNodo(repoUsuario.getNombre(),"usuario", repoUsuario.getId()));

		repoUsuario.getPermisoAgregados().forEach(pa -> {

			arrayNode.add(generaNodo(pa.getNombre(), "permiso", pa.getIdPermiso()));
			arrayEdges.add(generaRelacion(repoUsuario.getId(), pa.getIdPermiso(), "PERMISO_AGREGADO"));

		});

		repoUsuario.getPerfiles().forEach(p -> {

			arrayNode.add(generaNodo(p.getNombre(), "perfil", p.getIdPerfil()));
			arrayEdges.add(generaRelacion(repoUsuario.getId(), p.getIdPerfil(), "HAS_PERFIL"));

			p.getRoles().forEach(r -> {

				arrayNode.add(generaNodo(r.getNombreRol(), "rol", r.getIdRol()));
				arrayEdges.add(generaRelacion(p.getIdPerfil(), r.getIdRol(), "HAS_ROL"));

				r.getFacultades().forEach(pr ->{

					arrayNode.add(generaNodo(pr.getNombre(), "permiso", pr.getIdPermiso()));
					arrayEdges.add(generaRelacion(r.getIdRol(), pr.getIdPermiso(), "HAS_FACULTAD_ROL"));

				});
			});

		});


		System.out.println(generaGraph(arrayNode, arrayEdges).toString());

		return  null;

	}


	private ObjectNode generaGraph(ArrayNode arrayNodes, ArrayNode arrayRelationship){

		ObjectNode graphNodes = new ObjectMapper().createObjectNode();

		graphNodes.set("nodes", arrayNodes);
		graphNodes.set("edges", arrayRelationship);

		return  graphNodes;
	}

	private JsonNode generaNodo(String caption, String type, Long id){

		Map<String, Object> nodo  =  new HashMap<>();

		nodo.put("caption", caption);
		nodo.put("type", type);
		nodo.put("id", id);

		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNodeMap = mapper.convertValue(nodo, JsonNode.class);

		return jsonNodeMap;

	}

	private JsonNode generaRelacion(Long idStartNode, Long idEndNode, String etiquetaRelacion){

		Map<String, Object> relacion = new HashMap<>();

		relacion.put("source", idStartNode);
		relacion.put("target", idEndNode);
		relacion.put("caption", etiquetaRelacion);

		ObjectMapper mapperRelacion = new ObjectMapper();
		JsonNode jsonNodeRelacion = mapperRelacion.convertValue(relacion, JsonNode.class);

		return  jsonNodeRelacion;
	}

}
