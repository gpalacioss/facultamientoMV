package com.legosoft.facultamiento.models.old;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity(label = "Usuario")
public class Usuario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String idUsuario;
	
	private String nombre;
	
	private String telefono;
	
	private String email;
	
	private Boolean isInterno;
	
	private Boolean isActivo;
	
	@Relationship(type = "HAS_PERFIL", direction = Relationship.OUTGOING)
	private Set<Perfil> perfiles = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getIsInterno() {
		return isInterno;
	}

	public void setIsInterno(Boolean isInterno) {
		this.isInterno = isInterno;
	}
	
	

	public Boolean getIsActivo() {
		return isActivo;
	}

	public void setIsActivo(Boolean isActivo) {
		this.isActivo = isActivo;
	}

	public Set<Perfil> getPerfiles() {
		return perfiles;
	}

	public void setPerfiles(Set<Perfil> perfiles) {
		this.perfiles = perfiles;
	}
	
	
}
