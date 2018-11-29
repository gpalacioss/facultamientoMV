package com.legosoft.facultamiento.models.nuevo;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity(label = "Rol")
public class Rol implements Serializable {
	
	@Id
	@GeneratedValue
	private Long idRol;
	
	private String nombreRol;
	
	private Boolean isActivo;
	
	private Date fechaCreacion;
	
	@Relationship(type = "HAS_FACULTAD_ROL", direction = Relationship.OUTGOING)
	private Set<Permiso> facultades = new HashSet<>();

	public Long getIdRol() {
		return idRol;
	}

	public void setIdRol(Long idRol) {
		this.idRol = idRol;
	}

	public String getNombreRol() {
		return nombreRol;
	}

	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
	}

	public Boolean getIsActivo() {
		return isActivo;
	}

	public void setIsActivo(Boolean isActivo) {
		this.isActivo = isActivo;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Set<Permiso> getFacultades() {
		return facultades;
	}

	public void setFacultades(Set<Permiso> facultades) {
		this.facultades = facultades;
	}
	
	

}
