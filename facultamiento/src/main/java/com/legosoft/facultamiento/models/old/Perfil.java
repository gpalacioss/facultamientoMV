package com.legosoft.facultamiento.models.old;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;


@NodeEntity(label = "Perfil")
public class Perfil implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	
	private String idPerfil;

	private String nombre;
	
	private Boolean isActivo;
	
	@Relationship(type = "HAS_FACULTAD", direction = Relationship.OUTGOING)
	private Set<Facultad> facultades = new HashSet<>();	

	public Set<Facultad> getFacultades() {
		return facultades;
	}

	public void setFacultades(Set<Facultad> facultades) {
		this.facultades = facultades;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(String idPerfil) {
		this.idPerfil = idPerfil;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Boolean isActivo() {
		return isActivo;
	}

	public void setActivo(Boolean isActivo) {
		this.isActivo = isActivo;
	}
	
	public List<String> getNombresFac(){
		List<String> rList = new ArrayList<>();
		 getFacultades().stream().forEach(f -> rList.add(f.getNombre()));
		 return rList;
	}

}
