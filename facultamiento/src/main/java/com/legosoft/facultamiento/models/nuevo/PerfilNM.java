package com.legosoft.facultamiento.models.nuevo;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;


@NodeEntity(label = "Profile")
public class PerfilNM implements Serializable {


	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long idPerfil;

	private String nombre;

	private String descripcion;

	private Boolean isActivo;

	private Date fechaCreacion;

	private Boolean isPatron;

	private Boolean isMaestro;

	@Relationship(type = "HAS_ROLE", direction = Relationship.OUTGOING)
	private Set<Rol> roles = new HashSet<>();


	public Long getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(Long idPerfil) {
		this.idPerfil = idPerfil;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Boolean getActivo() {
		return isActivo;
	}

	public void setActivo(Boolean activo) {
		isActivo = activo;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Boolean getPatron() {
		return isPatron;
	}

	public void setPatron(Boolean patron) {
		isPatron = patron;
	}

	public Boolean getMaestro() {
		return isMaestro;
	}

	public void setMaestro(Boolean maestro) {
		isMaestro = maestro;
	}

	public Set<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}
}