package com.legosoft.facultamiento.models.old;

import java.io.Serializable;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.QueryResult;

@NodeEntity(label = "Facultad")
public class Facultad implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String idFacultad;
	
	private String nombre;
	
	private String horaInicio;
	
	private String horaFinal;
	
	private String isRestriccionHorario;
	
	private String tipoFacultad;
	
	private String fechaModificacion;
	
	private Boolean isActivo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdFacultad() {
		return idFacultad;
	}

	public void setIdFacultad(String idFacultad) {
		this.idFacultad = idFacultad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getHoraFinal() {
		return horaFinal;
	}

	public void setHoraFinal(String horaFinal) {
		this.horaFinal = horaFinal;
	}

	public String isRestriccionHorario() {
		return isRestriccionHorario;
	}

	public void setRestriccionHorario(String isRestriccionHorario) {
		this.isRestriccionHorario = isRestriccionHorario;
	}

	public String getTipoFacultad() {
		return tipoFacultad;
	}

	public void setTipoFacultad(String tipoFacultad) {
		this.tipoFacultad = tipoFacultad;
	}

	public String getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(String fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public Boolean isActivo() {
		return isActivo;
	}

	public void setActivo(Boolean isActivo) {
		this.isActivo = isActivo;
	}
	
	
	
}
