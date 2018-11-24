package com.legosoft.facultamiento.models.nuevo;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity(label = "FacultadNM")
public class FacultadNM {


    @Id
    @GeneratedValue
    private Long id;

    private Long idFacultad;

    private String nombre;

    private String descripcion;

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

    public Long getIdFacultad() {
        return idFacultad;
    }

    public void setIdFacultad(Long idFacultad) {
        this.idFacultad = idFacultad;
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

    public String getIsRestriccionHorario() {
        return isRestriccionHorario;
    }

    public void setIsRestriccionHorario(String isRestriccionHorario) {
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

    public Boolean getActivo() {
        return isActivo;
    }

    public void setActivo(Boolean activo) {
        isActivo = activo;
    }
}
